CREATE TABLE "user"
(
    id           BIGINT       NOT NULL,
    email        VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
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
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_phonenumber UNIQUE (phone_number);



CREATE TABLE confirmation_token
(
    id           BIGINT                      NOT NULL,
    token        VARCHAR(255)                NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expires_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    confirmed_at TIMESTAMP WITHOUT TIME ZONE,
    user_id      BIGINT                      NOT NULL,
    CONSTRAINT pk_confirmationtoken PRIMARY KEY (id)
);

ALTER TABLE confirmation_token
    ADD CONSTRAINT FK_CONFIRMATIONTOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE;



CREATE TABLE product
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    details    VARCHAR(255),
    category   VARCHAR(255),
    price      DOUBLE PRECISION,
    weight     DOUBLE PRECISION,
    popularity INTEGER,
    is_new     BOOLEAN,
    CONSTRAINT pk_product PRIMARY KEY (id)
);



CREATE TABLE "order"
(
    id        BIGINT                      NOT NULL,
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
    CONSTRAINT pk_order PRIMARY KEY (id)
);

ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_ID FOREIGN KEY (id) REFERENCES "user" (id) ON DELETE CASCADE;



CREATE TABLE order_products
(
    id         BIGINT NOT NULL,
    order_id   BIGINT,
    product_id BIGINT,
    CONSTRAINT pk_orderproducts PRIMARY KEY (id)
);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDERPRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE;

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDERPRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);
