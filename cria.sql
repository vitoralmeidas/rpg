CREATE TABLE tb_atributo (
    cd_atributo                 NUMBER(6) NOT NULL,
    cd_personagem               NUMBER NOT NULL,
    nm_atributo                 VARCHAR2(30) NOT NULL,
    vl_atributo                 NUMBER(6)
);

ALTER TABLE tb_atributo ADD CONSTRAINT pk_tb_atributo PRIMARY KEY (cd_atributo,cd_personagem);

CREATE TABLE tb_item (
    cd_item                     NUMBER(3) NOT NULL,
    cd_personagem               NUMBER NOT NULL,
    nm_item                     VARCHAR2(30),
    vl_item                     NUMBER(2),
    ds_item                     VARCHAR2(40)
);

ALTER TABLE tb_item ADD CONSTRAINT pk_tb_item PRIMARY KEY (cd_item, cd_personagem);

CREATE TABLE tb_personagem (
    cd_personagem  NUMBER(6) NOT NULL,
    nm_personagem  VARCHAR2(40),
    nm_raca        VARCHAR2(40),
    nm_classe      VARCHAR2(40),
    nm_guilda      VARCHAR2(40),
    vl_experiencia NUMBER(6),
    vl_level       NUMBER(6)
);

ALTER TABLE tb_personagem ADD CONSTRAINT pk_tb_personagem PRIMARY KEY ( cd_personagem );

ALTER TABLE tb_atributo
    ADD CONSTRAINT fk_atributo_personagem FOREIGN KEY (cd_personagem)
        REFERENCES tb_personagem (cd_personagem);

ALTER TABLE tb_item
    ADD CONSTRAINT fk_item_personagem FOREIGN KEY (cd_personagem)
        REFERENCES tb_personagem (cd_personagem);


create sequence SQ_ATRIBUTO nocache ;
create sequence SQ_ITEM nocache ;