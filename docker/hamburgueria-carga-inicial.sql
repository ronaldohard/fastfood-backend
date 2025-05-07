-- Usuários
INSERT INTO user (id, name, email_or_description)
VALUES (1, 'Ana', 'ana@fast.com');
INSERT INTO user (id, name, email_or_description)
VALUES (2, 'Bruno', 'bruno@fast.com');
INSERT INTO user (id, name, email_or_description)
VALUES (3, 'Clara', 'clara@fast.com');
INSERT INTO user (id, name, email_or_description)
VALUES (4, 'Daniel', 'daniel@fast.com');
INSERT INTO user (id, name, email_or_description)
VALUES (5, 'Eduarda', 'edu@fast.com');

-- Clientes
INSERT INTO customer (id, name, cpf)
VALUES (1, 'João Silva', '11111111111');
INSERT INTO customer (id, name, cpf)
VALUES (2, 'Maria Souza', '22222222222');
INSERT INTO customer (id, name, cpf)
VALUES (3, 'Carlos Lima', '33333333333');
INSERT INTO customer (id, name, cpf)
VALUES (4, 'Paula Rocha', '44444444444');
INSERT INTO customer (id, name, cpf)
VALUES (5, 'Lucas Mendes', '55555555555');

-- Produtos (Lanches, Bebidas, Sobremesas)
INSERT INTO product (id, name, category, price, description)
VALUES (1, 'X-Burger', 'LANCHE', 18.90, 'Hambúrguer clássico');
INSERT INTO product (id, name, category, price, description)
VALUES (2, 'X-Bacon', 'LANCHE', 21.50, 'Hambúrguer com bacon');
INSERT INTO product (id, name, category, price, description)
VALUES (3, 'Refrigerante', 'BEBIDA', 6.00, 'Lata 350ml');
INSERT INTO product (id, name, category, price, description)
VALUES (4, 'Suco Natural', 'BEBIDA', 8.00, 'Suco 300ml');
INSERT INTO product (id, name, category, price, description)
VALUES (5, 'Sundae Chocolate', 'SOBREMESA', 9.50, 'Sorvete com cobertura');

-- Pedidos
INSERT INTO "order" (id, customer_id, status, created_at, total)
VALUES (1, 1, 'RECEBIDO', now(), 25.90);
INSERT INTO "order" (id, customer_id, status, created_at, total)
VALUES (2, 2, 'EM_PREPARACAO', now(), 30.40);
INSERT INTO "order" (id, customer_id, status, created_at, total)
VALUES (3, 3, 'PRONTO', now(), 35.00);
INSERT INTO "order" (id, customer_id, status, created_at, total)
VALUES (4, 4, 'FINALIZADO', now(), 40.00);
INSERT INTO "order" (id, customer_id, status, created_at, total)
VALUES (5, 5, 'RECEBIDO', now(), 29.50);

-- Relacionar produtos aos pedidos
INSERT INTO order_products (order_id, products_id)
VALUES (1, 1);
INSERT INTO order_products (order_id, products_id)
VALUES (1, 3);
INSERT INTO order_products (order_id, products_id)
VALUES (2, 2);
INSERT INTO order_products (order_id, products_id)
VALUES (2, 4);
INSERT INTO order_products (order_id, products_id)
VALUES (3, 1);
INSERT INTO order_products (order_id, products_id)
VALUES (3, 5);
INSERT INTO order_products (order_id, products_id)
VALUES (4, 2);
INSERT INTO order_products (order_id, products_id)
VALUES (4, 3);
INSERT INTO order_products (order_id, products_id)
VALUES (5, 1);
INSERT INTO order_products (order_id, products_id)
VALUES (5, 4);