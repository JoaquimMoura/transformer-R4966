﻿INSERT INTO ${SCHEMA_NAME}.PESSOA (ID, DATA_NASCIMENTO, NOME, CPF, SEXO) VALUES (1, '1985-02-22', 'TONY STARK', '98030795068', 'M');
INSERT INTO ${SCHEMA_NAME}.PESSOA (ID, DATA_NASCIMENTO, NOME, CPF, SEXO) VALUES (2, '1945-01-30', 'STEVE ROGERS', '33314654022', 'M');

INSERT INTO ${SCHEMA_NAME}.EMAIL (EMAIL, PESSOA_ID) VALUES ('TONY@GMAIL.COM', 1);
INSERT INTO ${SCHEMA_NAME}.EMAIL (EMAIL, PESSOA_ID) VALUES ('STEVE@GMAIL.COM', 2);

INSERT INTO ${SCHEMA_NAME}.ENDERECO (BAIRRO, CEP, CIDADE, ENDERECO, NUMERO, UF, PESSOA_ID) VALUES ('BAIRRO DO TONY', '70306970', 'BRASILIA', 'QUADRA 01', 1, 'DF', 1);
INSERT INTO ${SCHEMA_NAME}.ENDERECO (BAIRRO, CEP, CIDADE, ENDERECO, NUMERO, UF, PESSOA_ID) VALUES ('BAIRRO DO STEVE', '70306970', 'BRASILIA', 'QUADRA 02', 2, 'DF', 2);

INSERT INTO ${SCHEMA_NAME}.TELEFONE (NUMERO, TIPO_TELEFONE, PESSOA_ID) VALUES ('61958959555', 'C', 1);
INSERT INTO ${SCHEMA_NAME}.TELEFONE (NUMERO, TIPO_TELEFONE, PESSOA_ID) VALUES ('61987654321', 'C', 2);

INSERT INTO ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO (DESCRICAO) VALUES ('Rendimentos');
INSERT INTO ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO (DESCRICAO) VALUES ('Contas');
INSERT INTO ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO (DESCRICAO) VALUES ('Mercado');
INSERT INTO ${SCHEMA_NAME}.CATEGORIA_MOVIMENTO (DESCRICAO) VALUES ('Veículo');

INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-01-01', 'D', 410, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-01-02', 'D', 460, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-02-03', 'D', 300, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-02-04', 'D', 200, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-03-05', 'D', 640, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-03-06', 'D', 330, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-04-07', 'D', 100, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-04-08', 'D', 220, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-05-09', 'D', 690, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-05-10', 'D', 201, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-06-11', 'D', 620, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-06-12', 'D', 700, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2023-07-13', 'D', 100, 1, 1);

INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-01-01', 'C', 410 / 2, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-01-02', 'C', 460 * 2, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-02-03', 'C', 300 / 2, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-02-04', 'C', 200 * 2, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-03-05', 'C', 640 / 2, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-03-06', 'C', 330 * 2, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-04-07', 'C', 100 / 2, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-04-08', 'C', 220 * 2, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-05-09', 'C', 690 / 2, 1, 1);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-05-10', 'C', 201 * 2, 1, 2);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-06-11', 'C', 620 / 2, 1, 3);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-06-12', 'C', 700 * 2, 1, 4);
INSERT INTO ${SCHEMA_NAME}.MOVIMENTO_FINANCEIRO (ID, DATA, TIPO_MOVIMENTO, VALOR, PESSOA_ID, CATEGORIA_ID) VALUES(DEFAULT, '2019-07-13', 'C', 100 / 2, 1, 1);