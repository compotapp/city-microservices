-- V1__create_table_number_sequences.sql

CREATE TABLE IF NOT EXISTS number_sequences
(
    type        VARCHAR(255) PRIMARY KEY NOT NULL,
    last_number INT                      NOT NULL,
    CONSTRAINT chk_last_number CHECK (last_number >= 0)
);