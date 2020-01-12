CREATE TABLE IF NOT EXISTS users (
    id LONG IDENTITY PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    phone_number VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS ingredients (
    id VARCHAR(4) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos (
    id LONG IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos_ingredients (
    taco_id BIGINT NOT NULL,
    ingredient_id VARCHAR(4) NOT NULL
);

ALTER TABLE tacos_ingredients
ADD FOREIGN KEY (taco_id) REFERENCES tacos(id);

ALTER TABLE tacos_ingredients
ADD FOREIGN KEY (ingredient_id) REFERENCES ingredients(id);

CREATE TABLE IF NOT EXISTS orders (
    id IDENTITY PRIMARY KEY,
    delivery_street VARCHAR(50) NOT NULL,
    delivery_city VARCHAR(50) NOT NULL,
    delivery_state VARCHAR(40) NOT NULL,
    delivery_zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    placed_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS orders_tacos (
    order_id BIGINT NOT NULL,
    taco_id BIGINT NOT NULL
);

ALTER TABLE orders_tacos
ADD FOREIGN KEY (order_id) REFERENCES orders(id);

ALTER TABLE orders_tacos
ADD FOREIGN KEY (taco_id) REFERENCES tacos(id);