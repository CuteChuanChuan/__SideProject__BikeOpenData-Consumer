package app

import cats.effect._
import config.ConfigManager
import consumer.KafkaTopicConsumer
import database.PostgresService
import fs2.kafka._
import util.JsonParser

import scala.concurrent.duration.DurationInt

object BikeConsumer extends IOApp.Simple {

  val run: IO[Unit] = {
    def processRecord(msg: CommittableConsumerRecord[IO, String, String]): IO[Unit] = {
      val kafkaKey     = msg.record.key
      val bikeDataList = JsonParser.parseBikeDataArray(msg.record.value)
      PostgresService.createTransactor
        .use(xa => PostgresService.insertBatchRecords(xa, kafkaKey, bikeDataList))
        .flatMap(count =>
          IO(println(s"Inserted $count records into DB for offset ${msg.offset.offsets}")))
    }

    def run(consumer: KafkaConsumer[IO, String, String]): IO[Unit] =
      consumer.subscribeTo(ConfigManager.Kafka.topic) >> consumer.stream
        .evalMap { msg =>
          processRecord(msg).as(msg.offset)
        }
        .through(commitBatchWithin(1000, 15.seconds))
        .compile
        .drain

    KafkaConsumer.resource(KafkaTopicConsumer.kafkaConsumerSettings).use(run)
  }

}
