INSERT INTO users (name, badge_code) VALUES
('Henrique', 123456),
('João', 789012);

INSERT INTO items (name, category, barcode, current_quantity, minimum_quantity) VALUES
('Álcool Gel', 'Higiene', 111111, 10, 2),
('Luvas', 'Proteção', 222222, 5, 1);

INSERT INTO consumptions (user_id, item_id, quantity, date)
VALUES
(1, 1, 2, CURRENT_TIMESTAMP),
(1, 2, 1, CURRENT_TIMESTAMP),
(2, 1, 1, CURRENT_TIMESTAMP),
(2, 2, 3, CURRENT_TIMESTAMP);
