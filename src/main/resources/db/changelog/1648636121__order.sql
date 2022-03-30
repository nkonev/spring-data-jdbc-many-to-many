CREATE TABLE purchase_order(
    id BIGSERIAL PRIMARY KEY,
    shipping_address TEXT NOT NULL
);
CREATE TABLE order_item(
    order_item_id BIGSERIAL PRIMARY KEY,
    purchase_order_id BIGINT REFERENCES purchase_order(id),
    quantity INT NOT NULL,
    product TEXT NOT NULL
);

INSERT INTO purchase_order (shipping_address) values
('Tbilisi'), -- 1
('Moscow'); -- 2

INSERT INTO order_item(purchase_order_id, quantity, product) VALUES
(1, 100, 'Tea'), -- 1
(1, 200, 'Coffee'), -- 2
(1, 2, 'Bread'); -- 3
