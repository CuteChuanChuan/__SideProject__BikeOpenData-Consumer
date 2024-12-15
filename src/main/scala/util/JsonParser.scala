package util

import com.fasterxml.jackson.databind.{ DeserializationFeature, ObjectMapper }
import com.fasterxml.jackson.module.scala.{ ClassTagExtensions, DefaultScalaModule }
import modal.BikeData

object JsonParser {

  private val mapper = new ObjectMapper() with ClassTagExtensions
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def parseBikeDataArray(jsonStr: String): List[BikeData] = {
    val rawList = mapper.readValue[List[Map[String, Any]]](jsonStr)

    rawList.map { m =>
      BikeData(
        sno = m.getOrElse("sno", "").toString,
        sna = m.getOrElse("sna", "").toString,
        sarea = m.getOrElse("sarea", "").toString,
        mday = m.getOrElse("mday", "").toString,
        ar = m.getOrElse("ar", "").toString,
        sareaen = m.getOrElse("sareaen", "").toString,
        snaen = m.getOrElse("snaen", "").toString,
        aren = m.getOrElse("aren", "").toString,
        act = m.getOrElse("act", "").toString,
        srcUpdateTime = m.getOrElse("srcUpdateTime", "").toString,
        updateTime = m.getOrElse("updateTime", "").toString,
        infoTime = m.getOrElse("infoTime", "").toString,
        infoDate = m.getOrElse("infoDate", "").toString,
        total = m.get("total").map(_.toString.toInt).getOrElse(0),
        available_rent_bikes = m.get("available_rent_bikes").map(_.toString.toInt).getOrElse(0),
        latitude = m.get("latitude").map(v => BigDecimal(v.toString)).getOrElse(BigDecimal(0)),
        longitude = m.get("longitude").map(v => BigDecimal(v.toString)).getOrElse(BigDecimal(0)),
        available_return_bikes = m.get("available_return_bikes").map(_.toString.toInt).getOrElse(0)
      )
    }
  }
}
