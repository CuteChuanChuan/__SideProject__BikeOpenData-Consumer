package modal

case class BikeData(
  sno:                    String,
  sna:                    String,
  sarea:                  String,
  mday:                   String,
  ar:                     String,
  sareaen:                String,
  snaen:                  String,
  aren:                   String,
  act:                    String,
  srcUpdateTime:          String,
  updateTime:             String,
  infoTime:               String,
  infoDate:               String,
  total:                  Int,
  available_rent_bikes:   Int,
  latitude:               BigDecimal,
  longitude:              BigDecimal,
  available_return_bikes: Int
)
