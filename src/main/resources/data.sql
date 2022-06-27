
delete from categories;
INSERT INTO categories(name) values ('Bebidas');
INSERT INTO categories(name) values ('Electronicos');

delete from products;
TRUNCATE  table products;
INSERT INTO products(name, description, stock, price, status, created_at, category)
values ('Agua Pura', 'Agua', 10, 15, '1', '2022-06-06', 1), ('Lenovo Tower', 'Tower PC', 10, 65000, '1', '2022-06-06', 2),
('Presidente', 'Cerveza', 10, 105, '1', '2022-06-06', 1);
