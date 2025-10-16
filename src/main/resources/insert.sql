INSERT INTO employee (id, first_name, last_name, email, phone_number, salary_per_hour, hours_worked, deleted) VALUES
(1, 'Anna', 'Kowalska', 'anna.kowalska@coffeeshop.pl', '600123456', 25.00, 160.00, false),
(2, 'Piotr', 'Nowak', 'piotr.nowak@coffeeshop.pl', '601987654', 26.50, 145.00, false),
(3, 'Katarzyna', 'Wiśniewska', 'katarzyna.wisniewska@coffeeshop.pl', '602543210', 27.00, 158.00, false),
(4, 'Tomasz', 'Lewandowski', 'tomasz.lewandowski@coffeeshop.pl', '603876543', 24.50, 172.00, false),
(5, 'Julia', 'Wójcik', 'julia.wojcik@coffeeshop.pl', '604112233', 25.00, 150.00, false);

INSERT INTO ingredient (id, name, unit, stock_quantity, unit_cost, deleted) VALUES
(1, 'Kawa ziarnista', 'G', 10000, 0.02, false),
(2, 'Mleko', 'ML', 100000, 0.003, false),
(3, 'Kubek papierowy 300ml', 'PCS', 200, 0.30, false),
(4, 'Wieczko', 'PCS', 200, 0.15, false),
(5, 'Syrop karmelowy', 'ml', 1000, 0.01, false);

INSERT INTO product (id, name, price, takeaway, deleted) VALUES
(1, 'Kawa Latte na wynos', 14.90, true, false),
(2, 'Kawa Latte', 14.90, false, false),
(3, 'Kawa Cappucino na wynos', 14.90, true, false),
(4, 'Kawa Cappucino', 13.90, false, false),
(5, 'Kawa Americano na wynos', 11.90, true, false),
(6, 'Kawa Americano', 11.90, false, false);

-- KAWA LATTE NA WYNOS (id=1)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (1, 1, 1, 9.00, false),   -- Kawa ziarnista 9g
                                                                                   (2, 1, 2, 250.00, false), -- Mleko 250ml
                                                                                   (3, 1, 3, 1.00, false),   -- Kubek papierowy
                                                                                   (4, 1, 4, 1.00, false),   -- Wieczko
                                                                                   (5, 1, 5, 15.00, false);  -- Syrop karmelowy

-- KAWA LATTE (na miejscu, id=2)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (6, 2, 1, 9.00, false),   -- Kawa ziarnista 9g
                                                                                   (7, 2, 2, 250.00, false); -- Mleko 250ml

-- KAWA CAPPUCCINO NA WYNOS (id=3)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (8, 3, 1, 9.00, false),   -- Kawa ziarnista 9g
                                                                                   (9, 3, 2, 200.00, false), -- Mleko 200ml
                                                                                   (10, 3, 3, 1.00, false),  -- Kubek papierowy
                                                                                   (11, 3, 4, 1.00, false);  -- Wieczko

-- KAWA CAPPUCCINO (na miejscu, id=4)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (12, 4, 1, 9.00, false),  -- Kawa ziarnista 9g
                                                                                   (13, 4, 2, 200.00, false);-- Mleko 200ml

-- KAWA AMERICANO NA WYNOS (id=5)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (14, 5, 1, 10.00, false),  -- Kawa ziarnista 10g
                                                                                   (15, 5, 2, 150.00, false), -- Mleko 150ml
                                                                                   (16, 5, 3, 1.00, false),   -- Kubek papierowy
                                                                                   (17, 5, 4, 1.00, false);   -- Wieczko

-- KAWA AMERICANO (na miejscu, id=6)
INSERT INTO product_component (id, product_id, ingredient_id, amount, deleted) VALUES
                                                                                   (18, 6, 1, 10.00, false),  -- Kawa ziarnista 10g
                                                                                   (19, 6, 2, 150.00, false); -- Mleko 150ml
