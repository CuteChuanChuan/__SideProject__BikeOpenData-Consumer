package config

import com.typesafe.config.{ Config, ConfigFactory }

object ConfigManager {

  private val config: Config = ConfigFactory.load()

  object Kafka {
    private val kafkaConfig: Config  = config.getConfig("kafka")
    val isDev:               Boolean = kafkaConfig.getBoolean("isDev")
    val topic:               String  = kafkaConfig.getString("topic")
    val enableAutoCommit:    Boolean = kafkaConfig.getBoolean("enable-auto-commit")
    val consumerGroupID:     String  = kafkaConfig.getString("consumerGroupID")
    val bootstrapServers:    String  = kafkaConfig.getString("bootstrap-servers")
    val username:            String  = kafkaConfig.getString("username")
    val password:            String  = kafkaConfig.getString("password")
    val securityProtocol:    String  = kafkaConfig.getString("security-protocol")
    val saslMechanism:       String  = kafkaConfig.getString("sasl-mechanism")
  }

  object Postgres {
    private val postgresConfig: Config = config.getConfig("database")
    val connStr:                String = postgresConfig.getString("url")
    val user:                   String = postgresConfig.getString("user")
    val password:               String = postgresConfig.getString("password")
    val table:                  String = postgresConfig.getString("table")
    val driver:                 String = "org.postgresql.Driver"
  }

}
