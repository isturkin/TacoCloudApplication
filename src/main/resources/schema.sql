CREATE TABLE IF NOT EXISTS ingredients (
    id VARCHAR(4) NOT NULL,
    name VARCHAR(25) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos (
    id IDENTITY,
    name VARCHAR(50) NOT NULL,
    createdDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS tacos_ingredients (
    taco BIGINT NOT NULL,
    ingredient VARCHAR(4) NOT NULL
);

ALTER TABLE tacos_ingredients
ADD FOREIGN KEY (taco) REFERENCES tacos(id);

ALTER TABLE tacos_ingredients
ADD FOREIGN KEY (ingredient) REFERENCES ingredients(id);

CREATE TABLE IF NOT EXISTS orders (
    id IDENTITY,
    deliveryName VARCHAR(50) NOT NULL,
    deliveryStreet VARCHAR(50) NOT NULL,
    deliveryCity VARCHAR(50) NOT NULL,
    deliveryState VARCHAR(40) NOT NULL,
    deliveryZip VARCHAR(10) NOT NULL,
    ccNumber VARCHAR(16) NOT NULL,
    ccExpiration VARCHAR(5) NOT NULL,
    ccCVV VARCHAR(3) NOT NULL,
    placedDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS orders_tacos (
    orderId BIGINT NOT NULL,
    tacoId BIGINT NOT NULL
);

ALTER TABLE orders_tacos
ADD FOREIGN KEY (orderId) REFERENCES orders(id);

ALTER TABLE orders_tacos
ADD FOREIGN KEY (tacoId) REFERENCES tacos(id);