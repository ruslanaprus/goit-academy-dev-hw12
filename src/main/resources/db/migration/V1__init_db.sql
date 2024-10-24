CREATE SEQUENCE IF NOT EXISTS seq_clients_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_tickets_id
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS clients (
	id BIGINT DEFAULT nextval('seq_clients_id'),
	name VARCHAR(200) NOT NULL CHECK (length(name) BETWEEN 3 AND 200),
    email VARCHAR(200) UNIQUE,
    CONSTRAINT pk_clients_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS planets (
	id VARCHAR(10) PRIMARY KEY CHECK (id ~ '^[A-Z0-9]+$'),
	name VARCHAR(500) NOT NULL UNIQUE CHECK (length(name) BETWEEN 1 AND 500)
);

CREATE TABLE IF NOT EXISTS tickets (
	id BIGINT DEFAULT nextval('seq_tickets_id'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id INTEGER NOT NULL,
    from_planet_id VARCHAR(10) NOT NULL,
    to_planet_id VARCHAR(10) NOT NULL,
    CONSTRAINT pk_tickets_id PRIMARY KEY (id),
    CONSTRAINT fk_client
        FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    CONSTRAINT fk_planet
        FOREIGN KEY (from_planet_id) REFERENCES planets(id) ON DELETE CASCADE,
        FOREIGN KEY (to_planet_id) REFERENCES planets(id) ON DELETE CASCADE
);