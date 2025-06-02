CREATE SEQUENCE public.cliente_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.cliente_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.cliente_id_seq TO postgres;

CREATE SEQUENCE public.ingrediente_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.ingrediente_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.ingrediente_id_seq TO postgres;

CREATE SEQUENCE public.tipo_pagamento_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.tipo_pagamento_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tipo_pagamento_id_seq TO postgres;

CREATE SEQUENCE public.tipo_produto_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.tipo_produto_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tipo_produto_id_seq TO postgres;

CREATE SEQUENCE public.pagamento_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.pagamento_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.pagamento_id_seq TO postgres;

CREATE SEQUENCE public.pedido_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.pedido_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.pedido_id_seq TO postgres;

CREATE SEQUENCE public.pedido_item_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.pedido_item_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.pedido_item_id_seq TO postgres;

CREATE SEQUENCE public.pedido_item_customizacao_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.pedido_item_customizacao_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.pedido_item_customizacao_id_seq TO postgres;

CREATE SEQUENCE public.produto_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.produto_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.produto_id_seq TO postgres;

CREATE SEQUENCE public.produto_ingrediente_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
ALTER SEQUENCE public.produto_ingrediente_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.produto_ingrediente_id_seq TO postgres;

CREATE TABLE public.cliente
(
    id   integer NOT NULL DEFAULT nextval('public.cliente_id_seq'),
    nome varchar(100) NULL,
    cel  varchar(20)  NULL,
    cpf  varchar(14)  NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);
ALTER TABLE public.cliente OWNER TO postgres;
GRANT ALL ON TABLE public.cliente TO postgres;

CREATE TABLE public.ingrediente
(
    id    integer NOT NULL DEFAULT nextval('public.ingrediente_id_seq'),
    nome  varchar(100)   NOT NULL,
    preco numeric(10,2)  NOT NULL,
    CONSTRAINT ingrediente_pkey PRIMARY KEY (id)
);
ALTER TABLE public.ingrediente OWNER TO postgres;
GRANT ALL ON TABLE public.ingrediente TO postgres;

CREATE TABLE public.tipo_pagamento
(
    id   integer NOT NULL DEFAULT nextval('public.tipo_pagamento_id_seq'),
    tipo varchar(50)  NOT NULL,
    CONSTRAINT tipo_pagamento_pkey PRIMARY KEY (id)
);
ALTER TABLE public.tipo_pagamento OWNER TO postgres;
GRANT ALL ON TABLE public.tipo_pagamento TO postgres;

CREATE TABLE public.tipo_produto
(
    id   integer NOT NULL DEFAULT nextval('public.tipo_produto_id_seq'),
    nome varchar(50)  NOT NULL,
    CONSTRAINT tipo_produto_pkey PRIMARY KEY (id)
);
ALTER TABLE public.tipo_produto OWNER TO postgres;
GRANT ALL ON TABLE public.tipo_produto TO postgres;

CREATE TABLE public.pagamento
(
    id                integer   NOT NULL DEFAULT nextval('public.pagamento_id_seq'),
    pedido_id         int8      NOT NULL,
    tipo_pagamento_id int4      NOT NULL,
    status            varchar(20) NOT NULL,
    valor_total       numeric(10,2) NOT NULL,
    qr_code_url       text      NULL,
    criado_em         timestamp DEFAULT now() NULL,
    CONSTRAINT pagamento_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pagamento_tipo FOREIGN KEY (tipo_pagamento_id) REFERENCES public.tipo_pagamento (id),
    CONSTRAINT pagamento_unique UNIQUE (pedido_id)
);
ALTER TABLE public.pagamento OWNER TO postgres;
GRANT ALL ON TABLE public.pagamento TO postgres;

CREATE TABLE public.pedido
(
    id           integer   NOT NULL DEFAULT nextval('public.pedido_id_seq'),
    cliente_id   int4      NULL,
    pagamento_id int4      NULL,
    "data"       timestamp DEFAULT now() NULL,
    valor_total  numeric(10,2)  NOT NULL,
    status       varchar   NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente (id),
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY (pagamento_id) REFERENCES public.pagamento (id)
);
ALTER TABLE public.pedido OWNER TO postgres;
GRANT ALL ON TABLE public.pedido TO postgres;

CREATE TABLE public.produto
(
    id              integer NOT NULL DEFAULT nextval('public.produto_id_seq'),
    nome            varchar(100) NOT NULL,
    tipo_produto_id int4   NOT NULL,
    preco           numeric(10,2) NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id),
    CONSTRAINT fk_produto_tipo FOREIGN KEY (tipo_produto_id) REFERENCES public.tipo_produto (id)
);
ALTER TABLE public.produto OWNER TO postgres;
GRANT ALL ON TABLE public.produto TO postgres;

CREATE TABLE public.produto_ingrediente
(
    id             integer NOT NULL DEFAULT nextval('public.produto_ingrediente_id_seq'),
    produto_id     int4    NOT NULL,
    ingrediente_id int4    NOT NULL,
    CONSTRAINT produto_ingrediente_pkey PRIMARY KEY (id),
    CONSTRAINT fk_produtoingrediente_ingrediente FOREIGN KEY (ingrediente_id) REFERENCES public.ingrediente (id),
    CONSTRAINT fk_produtoingrediente_produto    FOREIGN KEY (produto_id)     REFERENCES public.produto (id)
);
ALTER TABLE public.produto_ingrediente OWNER TO postgres;
GRANT ALL ON TABLE public.produto_ingrediente TO postgres;

CREATE TABLE public.pedido_item
(
    id         integer   NOT NULL DEFAULT nextval('public.pedido_item_id_seq'),
    pedido_id  int4      NOT NULL,
    produto_id int4      NOT NULL,
    quantidade int4      NOT NULL,
    preco      numeric(10,2) NOT NULL,
    CONSTRAINT pedido_item_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pedidoitem_pedido  FOREIGN KEY (pedido_id)  REFERENCES public.pedido (id),
    CONSTRAINT fk_pedidoitem_produto FOREIGN KEY (produto_id) REFERENCES public.produto (id)
);
ALTER TABLE public.pedido_item OWNER TO postgres;
GRANT ALL ON TABLE public.pedido_item TO postgres;

CREATE TABLE public.pedido_item_customizacao
(
    id             integer NOT NULL DEFAULT nextval('public.pedido_item_customizacao_id_seq'),
    pedido_item_id int4    NOT NULL,
    ingrediente_id int4    NOT NULL,
    tipo           varchar(20)      NOT NULL,
    preco          numeric(10,2)    NOT NULL,
    CONSTRAINT pedido_item_customizacao_pkey PRIMARY KEY (id),
    CONSTRAINT pedido_item_customizacao_tipo_check CHECK ( (tipo)::text = ANY (ARRAY['addition'::character varying, 'removal'::character varying]) ),
    CONSTRAINT fk_customizacao_ingrediente FOREIGN KEY (ingrediente_id)   REFERENCES public.ingrediente (id),
    CONSTRAINT fk_customizacao_item       FOREIGN KEY (pedido_item_id)    REFERENCES public.pedido_item (id)
);
ALTER TABLE public.pedido_item_customizacao OWNER TO postgres;
GRANT ALL ON TABLE public.pedido_item_customizacao TO postgres;


GRANT ALL ON SCHEMA public TO pg_database_owner;
GRANT USAGE ON SCHEMA public TO public;