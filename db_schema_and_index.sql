create table if not exists public.tableName
(
    id                     BIGSERIAL PRIMARY KEY,
    timeInsertedIntoKafka  BIGINT,
    sno                    VARCHAR(50),
    sna                    VARCHAR(100),
    sarea                  VARCHAR(50),
    mday                   VARCHAR(255),
    ar                     VARCHAR(255),
    sareaen                VARCHAR(100),
    snaen                  VARCHAR(100),
    aren                   VARCHAR(255),
    act                    VARCHAR(10),
    src_update_time        VARCHAR(255),
    update_time            VARCHAR(255),
    info_time              VARCHAR(255),
    info_date              VARCHAR(255),
    total                  INTEGER,
    available_rent_bikes   INTEGER,
    available_return_bikes INTEGER,
    latitude               DECIMAL,
    longitude              DECIMAL,
    time_inserted_to_db    TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
    );

CREATE INDEX idx_time_inserted_kafka
    ON bike_data_raw (timeInsertedIntoKafka);

CREATE INDEX idx_sno
    ON bike_data_raw (sno);

CREATE INDEX idx_act
    ON bike_data_raw (sno);