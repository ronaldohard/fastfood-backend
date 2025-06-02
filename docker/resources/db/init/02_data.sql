INSERT INTO tipo_produto (id, nome)
VALUES
    (nextval('public.tipo_produto_id_seq'), 'Hamburguer'),
    (nextval('public.tipo_produto_id_seq'), 'Acompanhamento'),
    (nextval('public.tipo_produto_id_seq'), 'Sobremesa'),
    (nextval('public.tipo_produto_id_seq'), 'Bebida');

INSERT INTO produto (id, nome, tipo_produto_id, preco)
VALUES
    (nextval('public.produto_id_seq'), 'X-Hamburguer',   1, 10.00),
    (nextval('public.produto_id_seq'), 'X-Bacon',        1, 12.00),
    (nextval('public.produto_id_seq'), 'Fritas',         2,  3.00),
    (nextval('public.produto_id_seq'), 'Batata Rústica', 2,  4.50),
    (nextval('public.produto_id_seq'), 'Pudim',          3,  5.00),
    (nextval('public.produto_id_seq'), 'Sorvete',        3,  6.00),
    (nextval('public.produto_id_seq'), 'Coca',           4,  5.00),
    (nextval('public.produto_id_seq'), 'Pepsi',          4,  4.50);

INSERT INTO ingrediente (id, nome, preco)
VALUES
    (nextval('public.ingrediente_id_seq'), 'Salada', 1.00),
    (nextval('public.ingrediente_id_seq'), 'Queijo', 1.00),
    (nextval('public.ingrediente_id_seq'), 'Tomate', 1.00),
    (nextval('public.ingrediente_id_seq'), 'Bacon',  2.00),
    (nextval('public.ingrediente_id_seq'), 'Ovo',    1.50);

INSERT INTO produto_ingrediente (id, produto_id, ingrediente_id)
VALUES
    (nextval('public.produto_ingrediente_id_seq'), 1, 1),
    (nextval('public.produto_ingrediente_id_seq'), 1, 2),
    (nextval('public.produto_ingrediente_id_seq'), 1, 3),
    (nextval('public.produto_ingrediente_id_seq'), 2, 2),
    (nextval('public.produto_ingrediente_id_seq'), 2, 4);

INSERT INTO cliente (id, nome, cel, cpf)
VALUES
    (nextval('public.cliente_id_seq'), 'Ronaldo', '11912345678', '11122233344');

INSERT INTO tipo_pagamento (id, tipo)
VALUES
    (nextval('public.tipo_pagamento_id_seq'), 'QR CODE'),
    (nextval('public.tipo_pagamento_id_seq'), 'Cartão');

INSERT INTO pagamento (id, tipo_pagamento_id, status, valor_total, qr_code_url, criado_em)
VALUES
    (nextval('public.pagamento_id_seq'), 1, 'pending', 58.00, 'https://pagamento.exemplo.com/qrcode/123456', '2025-05-15T21:30:00');

INSERT INTO pedido (id, cliente_id, pagamento_id, data, valor_total)
VALUES
    (nextval('public.pedido_id_seq'), 1, 1, '2025-05-15T21:30:00', 58.00);

INSERT INTO pedido_item (id, pedido_id, produto_id, quantidade, preco)
VALUES
    (nextval('public.pedido_item_id_seq'), 1, 1, 1, 10.00),
    (nextval('public.pedido_item_id_seq'), 1, 2, 1, 12.00),
    (nextval('public.pedido_item_id_seq'), 1, 3, 1,  3.00),
    (nextval('public.pedido_item_id_seq'), 1, 4, 1,  4.50),
    (nextval('public.pedido_item_id_seq'), 1, 5, 1,  5.00),
    (nextval('public.pedido_item_id_seq'), 1, 6, 1,  6.00),
    (nextval('public.pedido_item_id_seq'), 1, 7, 1,  5.00),
    (nextval('public.pedido_item_id_seq'), 1, 8, 1,  4.50);

INSERT INTO pedido_item_customizacao (id, pedido_item_id, ingrediente_id, tipo, preco)
VALUES
    (nextval('public.pedido_item_customizacao_id_seq'), 1, 4, 'addition', 2.00),
    (nextval('public.pedido_item_customizacao_id_seq'), 1, 5, 'addition', 1.50),
    (nextval('public.pedido_item_customizacao_id_seq'), 1, 1, 'removal',  0.00);