INSERT INTO tipo_produto (id, nome)
VALUES (1, 'Hamburguer'),
       (2, 'Acompanhamento'),
       (3, 'Sobremesa'),
       (4, 'Bebida');
INSERT INTO produto (id, nome, tipo_produto_id, preco)
VALUES (1, 'X-Hamburguer', 1, 10.00),
       (2, 'X-Bacon', 1, 12.00),
       (3, 'Fritas', 2, 3.00),
       (4, 'Batata Rústica', 2, 4.50),
       (5, 'Pudim', 3, 5.00),
       (6, 'Sorvete', 3, 6.00),
       (7, 'Coca', 4, 5.00),
       (8, 'Pepsi', 4, 4.50);
INSERT INTO ingrediente (id, nome, preco)
VALUES (1, 'Salada', 1.00),
       (2, 'Queijo', 1.00),
       (3, 'Tomate', 1.00),
       (4, 'Bacon', 2.00),
       (5, 'Ovo', 1.50);
INSERT INTO produto_ingrediente (id, produto_id, ingrediente_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 2),
       (5, 2, 4);
INSERT INTO cliente (id, nome, cel, cpf)
VALUES (1, 'Ronaldo', '11912345678', '11122233344');
INSERT INTO tipo_pagamento (id, tipo)
VALUES (1, 'QR CODE'),
       (2, 'Cartão');
INSERT INTO pagamento (id, tipo_pagamento_id, status, valor_total, qr_code_url, criado_em)
VALUES (1, 1, 'pending', 58.00, 'https://pagamento.exemplo.com/qrcode/123456', '2025-05-15T21:30:00');
INSERT INTO pedido (id, cliente_id, pagamento_id, data, valor_total)
VALUES (1, 1, 1, '2025-05-15T21:30:00', 58.00);
INSERT INTO pedido_item (id, pedido_id, produto_id, quantidade, preco_unitario)
VALUES (1, 1, 1, 1, 10.00),
       (2, 1, 2, 1, 12.00),
       (3, 1, 3, 1, 3.00),
       (4, 1, 4, 1, 4.50),
       (5, 1, 5, 1, 5.00),
       (6, 1, 6, 1, 6.00),
       (7, 1, 7, 1, 5.00),
       (8, 1, 8, 1, 4.50);
INSERT INTO pedido_item_customizacao (id, pedido_item_id, ingrediente_id, tipo, preco_extra)
VALUES (1, 1, 4, 'addition', 2.00),
       (2, 1, 5, 'addition', 1.50),
       (3, 1, 1, 'removal', 0.00);


SELECT *
FROM;