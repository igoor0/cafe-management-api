INSERT INTO ingredient (id, name, unit, stock_quantity, unit_cost, deleted) VALUES
(1, 'Kawa ziarnista', 'G', 1000, 0.02, false),
(2, 'Mleko', 'ML', 5000, 0.003, false),
(3, 'Kubek papierowy 300ml', 'PCS', 200, 0.30, false),
(4, 'Wieczko', 'PCS', 200, 0.15, false);

INSERT INTO product (id, name, price, takeaway, deleted) VALUES
(100, 'Kawa Latte na wynos', 14.90, true, false);

INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
(1001, 100, 1, 9, false),
(1002, 100, 2, 250, false),
(1003, 100, 3, 1, false),
(1004, 100, 4, 1, false);
