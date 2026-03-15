-- V5__create_function_update_stock_items.sql

CREATE OR REPLACE FUNCTION update_stock_items(
    product_ids BIGINT[],
    quantities INTEGER[]
)
    RETURNS TABLE
            (
                product_id BIGINT
            )
AS
$$
BEGIN
    RETURN QUERY
        WITH request AS (SELECT unnest(product_ids) AS product_id,
                                unnest(quantities)      AS quantity),
             update AS (UPDATE stock_items si
                 SET quantity = si.quantity + r.quantity, last_modified_date = now()
                 FROM request r
                 WHERE r.product_id = si.product_id AND si.quantity + r.quantity >= 0
                 RETURNING si.product_id)
        SELECT *
        FROM update;
END;
$$ LANGUAGE plpgsql;