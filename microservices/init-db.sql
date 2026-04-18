-- Runs once on first MySQL container startup.
-- Spring Boot will create tables automatically via ddl-auto=update.

CREATE DATABASE IF NOT EXISTS auth_service_db;
CREATE DATABASE IF NOT EXISTS length_service_db;
CREATE DATABASE IF NOT EXISTS weight_volume_service_db;
CREATE DATABASE IF NOT EXISTS temperature_service_db;
