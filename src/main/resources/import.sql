--Users
INSERT INTO tb_user (name, email, password) VALUES ('Bob admin', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana user', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

--Phones
INSERT INTO tb_phone (name, phone) VALUES ('casa', '11999000000');
INSERT INTO tb_phone (name, phone) VALUES ('celular', '11999123456');
INSERT INTO tb_phone (name, phone) VALUES ('casa', '11999000000');
INSERT INTO tb_phone (name, phone) VALUES ('celular', '11999123456');

INSERT INTO tb_user_phone (user_id, phone_id) VALUES (1, 1);
INSERT INTO tb_user_phone (user_id, phone_id) VALUES (1, 2);
INSERT INTO tb_user_phone (user_id, phone_id) VALUES (2, 3);
INSERT INTO tb_user_phone (user_id, phone_id) VALUES (2, 4);

--Address
INSERT INTO tb_address (name, address, number) VALUES ('casa', 'Rua limão', 123);
INSERT INTO tb_address (name, address, number) VALUES ('trabalho', 'Rua job', 112);
INSERT INTO tb_address (name, address, number) VALUES ('casa', 'Rua Brasil', 333);

INSERT INTO tb_user_address (user_id, address_id) VALUES (1, 1);
INSERT INTO tb_user_address (user_id, address_id) VALUES (1, 2);
INSERT INTO tb_user_address (user_id, address_id) VALUES (2, 3);

--Roles
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);

--Products
INSERT INTO tb_product (name, price, description, img_url) VALUES ('Pizza de 4 queijos', 40.0, 'Deliciosa pizza assada em forno a lenha', 'https://st2.depositphotos.com/1692343/5636/i/600/depositphotos_56360353-stock-photo-hot-homemade-pepperoni-pizza.jpg');
INSERT INTO tb_product (name, price, description, img_url) VALUES ('Pizza de calabresa', 35.0, 'Deliciosa pizza assada em forno a lenha', 'https://st2.depositphotos.com/1692343/5636/i/600/depositphotos_56360353-stock-photo-hot-homemade-pepperoni-pizza.jpg');
INSERT INTO tb_product (name, price, description, img_url) VALUES ('Pizza de marguerita', 45.0, 'Deliciosa pizza assada em forno a lenha', 'https://st2.depositphotos.com/1692343/5636/i/600/depositphotos_56360353-stock-photo-hot-homemade-pepperoni-pizza.jpg');
INSERT INTO tb_product (name, price, description, img_url) VALUES ('Pizza de mussarela', 30.0, 'Deliciosa pizza assada em forno a lenha', 'https://st2.depositphotos.com/1692343/5636/i/600/depositphotos_56360353-stock-photo-hot-homemade-pepperoni-pizza.jpg');

INSERT INTO tb_product (name, price, description, img_url) VALUES ('Hamburguer de costela', 25.0, 'Delicioso hamburguer caseiro feito na chapa', 'https://sinthoresp.com.br/site/wp-content/uploads/2014/04/size_590_big-tasty.jpg');
INSERT INTO tb_product (name, price, description, img_url) VALUES ('Hamburguer de picanha', 30.0, 'Delicioso hamburguer caseirofeito na chapa', 'https://sinthoresp.com.br/site/wp-content/uploads/2014/04/size_590_big-tasty.jpg');

--Categories
INSERT INTO tb_category (name) VALUES ('Pizza');
INSERT INTO tb_category (name) VALUES ('Hamburguer');
INSERT INTO tb_category (name) VALUES ('Pastel');
INSERT INTO tb_category (name) VALUES ('Batata');
INSERT INTO tb_category (name) VALUES ('Hot-Dog');

--Products And Categories
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 2);

--



