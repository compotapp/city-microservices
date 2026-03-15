-- V3__create_table_orders.sql

CREATE TABLE IF NOT EXISTS orders
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    owner              VARCHAR(255)          NOT NULL UNIQUE,
    number             VARCHAR(255)          NOT NULL UNIQUE,
    status             VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL
);

CREATE INDEX idx_order_status
    ON orders (status);

-- CREATE INDEX idx_order_status_1
--     ON orders(status, is_task_created)
--     WHERE status = 'RESERVED' AND is_task_created = false;