CREATE TABLE users
(
    id           BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
    email        VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    role         VARCHAR(255),
    locked       BOOLEAN,
    enabled      BOOLEAN,
    city         VARCHAR(255),
    street       VARCHAR(255),
    house        VARCHAR(255),
    apartment    VARCHAR(255),
    floor        VARCHAR(255),
    entrance     VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE confirmation_token
(
    id           BIGINT                      NOT NULL GENERATED ALWAYS AS IDENTITY,
    token        VARCHAR(255)                NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expires_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    confirmed_at TIMESTAMP WITHOUT TIME ZONE,
    user_id      BIGINT                      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE product
(
    id         BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    details    VARCHAR(255),
    category   VARCHAR(255),
    price      DOUBLE PRECISION,
    weight     DOUBLE PRECISION,
    popularity INTEGER,
    is_new     BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id        BIGINT                      NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id   BIGINT                      NOT NULL,
    status    VARCHAR(255),
    payment   VARCHAR(255),
    cost      DOUBLE PRECISION            NOT NULL,
    date      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    city      VARCHAR(255),
    street    VARCHAR(255),
    house     VARCHAR(255),
    apartment VARCHAR(255),
    floor     VARCHAR(255),
    entrance  VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_products
(
    id         BIGINT NOT NULL,
    order_id   BIGINT,
    product_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id)
);