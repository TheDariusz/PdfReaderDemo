ALTER TABLE meteo_alert
ADD CONSTRAINT meteo_alert_unique_id UNIQUE (voivodeship_id, alert_number, published_at);