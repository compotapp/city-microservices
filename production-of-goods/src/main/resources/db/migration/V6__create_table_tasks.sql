-- V6__create_table_tasks.sql

CREATE TABLE IF NOT EXISTS tasks
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    number             VARCHAR(255)          NOT NULL UNIQUE,
    product_id         BIGINT                NOT NULL,
    order_id           BIGINT                NULL,
    quantity           INT                   NOT NULL,
    complete_quantity  INT                   NOT NULL,
    type               VARCHAR(255)          NOT NULL,
    status             VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL,
    CONSTRAINT chk_quantity CHECK (quantity > 0),
    CONSTRAINT chk_complete_quantity CHECK (complete_quantity >= 0)
);