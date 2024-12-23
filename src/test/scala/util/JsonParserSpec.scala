package util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class JsonParserSpec extends AnyFlatSpec with Matchers {

  "parseBikeDataArray" should "return a list of BikeData" in {
    val jsonStr =
      s"""
         |[
         |  {
         |    "sno": "500101001",
         |    "sna": "YouBike2.0_捷運科技大樓站",
         |    "sarea": "大安區",
         |    "mday": "2024-11-23 00:35:15",
         |    "ar": "復興南路二段235號前",
         |    "sareaen": "Daan Dist.",
         |    "snaen": "YouBike2.0_MRT Technology Bldg. Sta.",
         |    "aren": "No.235， Sec. 2， Fuxing S. Rd.",
         |    "act": "1",
         |    "srcUpdateTime": "2024-11-23 00:39:22",
         |    "updateTime": "2024-11-23 00:39:52",
         |    "infoTime": "2024-11-23 00:35:15",
         |    "infoDate": "2024-11-23",
         |    "total": 28,
         |    "available_rent_bikes": 13,
         |    "latitude": 25.02605,
         |    "longitude": 121.5436,
         |    "available_return_bikes": 15
         |  },
         |  {
         |    "sno": "500101002",
         |    "sna": "YouBike2.0_復興南路二段273號前",
         |    "sarea": "大安區",
         |    "mday": "2024-11-23 00:39:14",
         |    "ar": "復興南路二段273號西側",
         |    "sareaen": "Daan Dist.",
         |    "snaen": "YouBike2.0_No.273， Sec. 2， Fuxing S. Rd.",
         |    "aren": "No.273， Sec. 2， Fuxing S. Rd. (West)",
         |    "act": "1",
         |    "srcUpdateTime": "2024-11-23 00:39:22",
         |    "updateTime": "2024-11-23 00:39:52",
         |    "infoTime": "2024-11-23 00:39:14",
         |    "infoDate": "2024-11-23",
         |    "total": 21,
         |    "available_rent_bikes": 8,
         |    "latitude": 25.02565,
         |    "longitude": 121.54357,
         |    "available_return_bikes": 12
         |  },
         |  {
         |    "sno": "500101003",
         |    "sna": "YouBike2.0_國北教大實小東側門",
         |    "sarea": "大安區",
         |    "mday": "2024-11-23 00:35:15",
         |    "ar": "和平東路二段96巷7號",
         |    "sareaen": "Daan Dist.",
         |    "snaen": "YouBike2.0_NTUE Experiment Elementary School (East)",
         |    "aren": "No. 7， Ln. 96， Sec. 2， Heping E. Rd",
         |    "act": "1",
         |    "srcUpdateTime": "2024-11-23 00:39:22",
         |    "updateTime": "2024-11-23 00:39:52",
         |    "infoTime": "2024-11-23 00:35:15",
         |    "infoDate": "2024-11-23",
         |    "total": 16,
         |    "available_rent_bikes": 9,
         |    "latitude": 25.02429,
         |    "longitude": 121.54124,
         |    "available_return_bikes": 6
         |  }
         |]
         |""".stripMargin

    val result = JsonParser.parseBikeDataArray(jsonStr)

    result should have size 3

    val firstElement = result.head
    firstElement.sno shouldBe "500101001"
    firstElement.sna shouldBe "YouBike2.0_捷運科技大樓站"
    firstElement.sarea shouldBe "大安區"
    firstElement.total shouldBe 28
    firstElement.available_rent_bikes shouldBe 13
    firstElement.latitude shouldBe BigDecimal(25.02605)
    firstElement.longitude shouldBe BigDecimal(121.5436)
    firstElement.available_return_bikes shouldBe 15
  }

}
