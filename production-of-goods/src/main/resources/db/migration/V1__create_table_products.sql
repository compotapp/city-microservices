-- V1__create_table_products.sql

CREATE TABLE IF NOT EXISTS products
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    number             VARCHAR(255)           NOT NULL UNIQUE,
    name               VARCHAR(255)           NOT NULL UNIQUE,
    category           VARCHAR(255)           NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL
);