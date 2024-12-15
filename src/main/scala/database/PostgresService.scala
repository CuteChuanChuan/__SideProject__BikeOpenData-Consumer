package database

import doobie.hikari.HikariTransactor
import doobie.implicits._
import cats.effect._
import config.ConfigManager
import doobie.Update
import modal.BikeData

import scala.concurrent.ExecutionContext

object PostgresService {

  implicit val connectEC: ExecutionContext =
    ExecutionContext.global

  def createTransactor: Resource[IO, HikariTransactor[IO]] =
    HikariTransactor.newHikariTransactor[IO](
      ConfigManager.Postgres.driver,
      ConfigManager.Postgres.connStr,
      ConfigManager.Postgres.user,
      ConfigManager.Postgres.password,
      connectEC
    )

  def insertBatchRecords(
    xa:           HikariTransactor[IO],
    kafkaKey:     String,
    bikeDataList: List[BikeData]): IO[Int] = {
    val sql =
      s"""
      INSERT INTO ${ConfigManager.Postgres.table} (
        timeInsertedIntoKafka, sno, sna, sarea, mday, ar, sareaen, snaen, aren, act,
        src_update_time, update_time, info_time, info_date, total,
        available_rent_bikes, latitude, longitude, available_return_bikes
      ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
      """

    val data =
      bikeDataList.map { bd =>
        (
          kafkaKey.toLong,
          bd.sno,
          bd.sna,
          bd.sarea,
          bd.mday,
          bd.ar,
          bd.sareaen,
          bd.snaen,
          bd.aren,
          bd.act,
          bd.srcUpdateTime,
          bd.updateTime,
          bd.infoTime,
          bd.infoDate,
          bd.total,
          bd.available_rent_bikes,
          bd.latitude,
          bd.longitude,
          bd.available_return_bikes
        )
      }

    Update[(
      Long,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      String,
      Int,
      Int,
      BigDecimal,
      BigDecimal,
      Int)](sql)
      .updateMany(data)
      .transact(xa)
  }
}
