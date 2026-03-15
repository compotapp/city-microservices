-- V4__create_table_order_items.sql

CREATE TABLE IF NOT EXISTS order_items
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    order_id           BIGINT                NOT NULL,
    product_id         BIGINT                NOT NULL,
    quantity           INT                   NOT NULL,
    status             VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL,
    CONSTRAINT chk_quantity CHECK (quantity > 0),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
)