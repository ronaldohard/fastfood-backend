CREATE TABLE cliente (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cel  VARCHAR(20),
    cpf  VARCHAR(14)
);

CREATE TABLE ingrediente (
    id    INT PRIMARY KEY AUTO_INCREMENT,
    nome  VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE tipo_pagamento (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE tipo_produto (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE pagamento (
    id                INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id         INT NOT NULL,
    tipo_pagamento_id INT NOT NULL,
    status            VARCHAR(20) NOT NULL,
    valor_total       DECIMAL(10, 2) NOT NULL,
    qr_code_url       CLOB,
    criado_em         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pagamento_tipo FOREIGN KEY (tipo_pagamento_id) REFERENCES tipo_pagamento(id),
    CONSTRAINT pagamento_unique UNIQUE (pedido_id)
);

CREATE TABLE pedido (
    id           INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id   INT,
    pagamento_id INT,
    data         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total  DECIMAL(10, 2) NOT NULL,
    status       VARCHAR(255),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY (pagamento_id) REFERENCES pagamento(id)
);

CREATE TABLE produto (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    nome            VARCHAR(100) NOT NULL,
    tipo_produto_id INT NOT NULL,
    preco           DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_produto_tipo FOREIGN KEY (tipo_produto_id) REFERENCES tipo_produto(id)
);

CREATE TABLE produto_ingrediente (
    id             INT PRIMARY KEY AUTO_INCREMENT,
    produto_id     INT NOT NULL,
    ingrediente_id INT NOT NULL,
    CONSTRAINT fk_produtoingrediente_ingrediente FOREIGN KEY (ingrediente_id) REFERENCES ingrediente(id),
    CONSTRAINT fk_produtoingrediente_produto   FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE pedido_item (
    id         INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id  INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco      DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedidoitem_pedido  FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    CONSTRAINT fk_pedidoitem_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE pedido_item_customizacao (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    pedido_item_id  INT NOT NULL,
    ingrediente_id  INT NOT NULL,
    tipo            VARCHAR(20) NOT NULL,
    preco           DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pedido_item_customizacao_tipo_check
        CHECK (tipo IN ('addition', 'removal')),
    CONSTRAINT fk_customizacao_ingrediente FOREIGN KEY (ingrediente_id) REFERENCES ingrediente(id),
    CONSTRAINT fk_customizacao_item       FOREIGN KEY (pedido_item_id) REFERENCES pedido_item(id)
);