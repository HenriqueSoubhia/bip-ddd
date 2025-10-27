-- Tabela de usuários
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    badge VARCHAR(50) NOT NULL
);

-- Tabela de itens
CREATE TABLE items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    barcode BIGINT,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    current_quantity INT,
    minimum_quantity INT
);

-- Tabela de registros de consumo
CREATE TABLE consumption_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    item_id BIGINT,
    quantity INT,
    consumption_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);


-- Usuários
INSERT INTO users (name, badge) VALUES ('Alice', 'A123');
INSERT INTO users (name, badge) VALUES ('Bob', 'B456');
INSERT INTO users (name, badge) VALUES ('Carlos', 'C789');

-- Itens
INSERT INTO items (barcode, name, category, current_quantity, minimum_quantity) VALUES (1001, 'Caneta', 'Escrita', 50, 10);
INSERT INTO items (barcode, name, category, current_quantity, minimum_quantity) VALUES (1002, 'Caderno', 'Papelaria', 30, 5);
INSERT INTO items (barcode, name, category, current_quantity, minimum_quantity) VALUES (1003, 'Lápis', 'Escrita', 100, 20);

-- Registros de consumo
INSERT INTO consumption_records (user_id, item_id, quantity, consumption_date) VALUES (1, 1, 3, CURRENT_TIMESTAMP);
INSERT INTO consumption_records (user_id, item_id, quantity, consumption_date) VALUES (2, 2, 1, CURRENT_TIMESTAMP);
INSERT INTO consumption_records (user_id, item_id, quantity, consumption_date) VALUES (3, 3, 5, CURRENT_TIMESTAMP);
