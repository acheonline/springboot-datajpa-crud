DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE customers
(
	id   INTEGER PRIMARY KEY default nextval('global_seq'),
	name VARCHAR NOT NULL
);
CREATE UNIQUE INDEX customers_name_idx ON customers (id, name);

CREATE TABLE orders
(
	id          INTEGER PRIMARY KEY default nextval('global_seq'),
	date_time   TIMESTAMP           DEFAULT now() NOT NULL,
	customer_id INTEGER                           NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);