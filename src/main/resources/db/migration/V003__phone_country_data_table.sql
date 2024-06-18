CREATE TABLE IF NOT EXISTS neotech.phone_country_data
(
    id SERIAL PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    
    phone_world_zone_id BIGINT NOT NULL,
    country_name VARCHAR(128) NOT NULL,
    dialing_code VARCHAR(16) NOT NULL,
    
    FOREIGN KEY (phone_world_zone_id) REFERENCES neotech.phone_world_zone(id)
);

CREATE INDEX IF NOT EXISTS idx_country_name ON neotech.phone_country_data(country_name);
CREATE INDEX IF NOT EXISTS idx_dialing_code ON neotech.phone_country_data(dialing_code);