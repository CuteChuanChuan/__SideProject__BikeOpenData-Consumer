package consumer

import fs2.kafka._
import cats.effect._
import config.ConfigManager

object KafkaTopicConsumer {

  def kafkaConsumerSettings: ConsumerSettings[IO, String, String] =
    ConsumerSettings[IO, String, String]
      .withBootstrapServers(ConfigManager.Kafka.bootstrapServers)
      .withGroupId(ConfigManager.Kafka.consumerGroupID)
      .withAutoOffsetReset(AutoOffsetReset.Earliest)
      .withEnableAutoCommit(ConfigManager.Kafka.enableAutoCommit)
      .withProperty("security.protocol", ConfigManager.Kafka.securityProtocol)
      .withProperty("sasl.mechanism", ConfigManager.Kafka.saslMechanism)
      .withProperty(
        "sasl.jaas.config",
        s"""org.apache.kafka.common.security.plain.PlainLoginModule required
           |username="${ConfigManager.Kafka.username}"
           |password="${ConfigManager.Kafka.password}";""".stripMargin
      )
}
