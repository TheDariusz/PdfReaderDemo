CREATE TABLE voivodeship
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL
);

CREATE TABLE alert_type
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE alert_status
(
    id     BIGSERIAL    NOT NULL PRIMARY KEY,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE meteo_alert
(
    id             BIGSERIAL NOT NULL PRIMARY KEY,
    voivodeship_id BIGINT    NOT NULL,
    alert_number   INTEGER   NOT NULL,
    published_at   TIMESTAMP NOT NULL,

    CONSTRAINT fk_meteo_alert_voivodeship_id FOREIGN KEY (voivodeship_id) REFERENCES voivodeship (id) ON UPDATE CASCADE
);

CREATE TABLE local_meteo_alert
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    meteo_alert_id  BIGINT    NOT NULL,
    alert_type_id   BIGINT    NOT NULL,
    alert_status_id BIGINT    NOT NULL,
    start_date      TIMESTAMP NOT NULL,
    stop_date       TIMESTAMP,
    description     TEXT,
    degree          INTEGER,
    sms             TEXT,

    CONSTRAINT fk_local_meteo_alert_meteo_alert_id FOREIGN KEY (meteo_alert_id) REFERENCES meteo_alert (id) ON DELETE CASCADE,
    CONSTRAINT fk_local_meteo_alert_alert_type_id FOREIGN KEY (alert_type_id) REFERENCES alert_type (id) ON UPDATE CASCADE,
    CONSTRAINT fk_local_meteo_alert_alert_status_id FOREIGN KEY (alert_status_id) REFERENCES alert_status (id) ON UPDATE CASCADE
);

CREATE TABLE county
(
    id                   BIGSERIAL    NOT NULL PRIMARY KEY,
    voivodeship_id       BIGINT       NOT NULL,
    name                 VARCHAR(255) NOT NULL,
    teryt_id             VARCHAR(255) NOT NULL,

    CONSTRAINT fk_county_voivodeship_id FOREIGN KEY (voivodeship_id) REFERENCES voivodeship (id) ON UPDATE CASCADE

);

CREATE TABLE local_alert_county
(
    local_meteo_alert_id BIGINT NOT NULL,
    county_id            BIGINT NOT NULL,

    CONSTRAINT fk_local_alert_county_local_meteo_alert_id FOREIGN KEY (local_meteo_alert_id) REFERENCES local_meteo_alert (id) ON DELETE CASCADE,
    CONSTRAINT fk_local_alert_county_county_id FOREIGN KEY (county_id) REFERENCES county (id) ON UPDATE CASCADE,
    CONSTRAINT local_alert_county_unique UNIQUE (local_meteo_alert_id, county_id)
);





