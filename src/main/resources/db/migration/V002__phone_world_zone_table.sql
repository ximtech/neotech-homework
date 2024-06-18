CREATE TABLE IF NOT EXISTS neotech.phone_world_zone
(
    id SERIAL PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    
    zone_id VARCHAR(8) NOT NULL UNIQUE,
    zone_name VARCHAR(255) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_zone_id ON neotech.phone_world_zone(zone_id);