-- V1__create_table_employees.sql

CREATE TABLE IF NOT EXISTS employees
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    number             VARCHAR(255)          NOT NULL UNIQUE,
    level              INT                   NOT NULL,
    exp                INT                   NOT NULL,
    type               VARCHAR(255)          NOT NULL,
    status             VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL,
    CONSTRAINT chk_lvl CHECK (level >= 2),
    CONSTRAINT chk_exp CHECK (exp >= 0)
);

CREATE INDEX idx_empl_type_status_created
    ON employees (type, status, created_date ASC);