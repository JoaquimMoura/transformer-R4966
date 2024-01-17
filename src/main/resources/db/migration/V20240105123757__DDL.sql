CREATE SCHEMA IF NOT EXISTS API;

CREATE TABLE ${SCHEMA_NAME}.PESSOA
(
    ID              BIGSERIAL    NOT NULL,
    CPF             VARCHAR(11)  NOT NULL,
    DATA_NASCIMENTO DATE         NOT NULL,
    NOME            VARCHAR(200) NOT NULL,
    SEXO            CHAR(1)      NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ${SCHEMA_NAME}.ENDERECO
(
    ID        BIGSERIAL NOT NULL,
    BAIRRO    VARCHAR(100),
    CEP       VARCHAR(8),
    CIDADE    VARCHAR(100),
    ENDERECO  VARCHAR(200),
    NUMERO    INT4,
    UF        VARCHAR(2),
    PESSOA_ID INT8      NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE ${SCHEMA_NAME}.ENDERECO
    ADD CONSTRAINT UK_ENDERECO_01 UNIQUE (PESSOA_ID);

ALTER TABLE ${SCHEMA_NAME}.ENDERECO
    ADD CONSTRAINT FK_ENDERECO_PESSOA_ID FOREIGN KEY (PESSOA_ID) REFERENCES ${SCHEMA_NAME}.PESSOA;

CREATE TABLE ${SCHEMA_NAME}.EMAIL
(
    ID        BIGSERIAL    NOT NULL,
    EMAIL     VARCHAR(200) NOT NULL,
    PESSOA_ID INT8         NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE ${SCHEMA_NAME}.EMAIL
    ADD CONSTRAINT FK_EMAIL_PESSOA_ID FOREIGN KEY (PESSOA_ID) REFERENCES ${SCHEMA_NAME}.PESSOA;

CREATE TABLE ${SCHEMA_NAME}.TELEFONE
(
    ID            BIGSERIAL   NOT NULL,
    NUMERO        VARCHAR(11) NOT NULL,
    TIPO_TELEFONE CHAR(1)     NOT NULL,
    PESSOA_ID     INT8        NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE ${SCHEMA_NAME}.TELEFONE
    ADD CONSTRAINT FK_TELEFONE_PESSOA_ID FOREIGN KEY (PESSOA_ID) REFERENCES ${SCHEMA_NAME}.PESSOA;

CREATE TABLE ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO
(
    ID        BIGSERIAL    NOT NULL,
    DESCRICAO VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO
(
    ID             BIGSERIAL     NOT NULL,
    DATA           TIMESTAMP     NOT NULL,
    TIPO_MOVIMENTO CHAR(1)       NOT NULL,
    VALOR          NUMERIC(8, 2) NOT NULL,
    CATEGORIA_ID   INT8          NOT NULL,
    PESSOA_ID      INT8          NOT NULL,
    PRIMARY KEY (ID)
);
ALTER TABLE ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO
    ADD CONSTRAINT FK_MOV_FIN_CATEGORIA_MOV_ID FOREIGN KEY (CATEGORIA_ID) REFERENCES ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO;
ALTER TABLE ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO
    ADD CONSTRAINT FK_MOV_FIN_PESSOA_ID FOREIGN KEY (PESSOA_ID) REFERENCES ${SCHEMA_NAME}.PESSOA;
