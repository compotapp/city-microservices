-- V2__create_table_stock_items.sql

CREATE TABLE IF NOT EXISTS stock_items
(
    id                   BIGSERIAL PRIMARY KEY NOT NULL,
    product_id           BIGINT                NOT NULL,
    quantity             INT                   NOT NULL,
    min_stock            INT                   NOT NULL,
    max_stock            INT                   NOT NULL,
    created_date         TIMESTAMP             NOT NULL,
    last_modified_date   TIMESTAMP             NOT NULL,
    CONSTRAINT chk_quantity CHECK (quantity >= 0),
    FOREIGN KEY (product_id) REFERENCES products (id)
)