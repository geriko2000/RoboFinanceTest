CREATE TABLE address (
    id bigserial NOT NULL,
    country varchar(255),
    region varchar(255),
    city varchar(255),
    street varchar(255),
    house varchar(255),
    flat varchar(255),
    created timestamp,
    modified timestamp,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE customer (
    id bigserial NOT NULL,
    registred_address_id bigint NOT NULL,
    actual_address_id bigint NOT NULL,
    first_name varchar(255) NULL,
    last_name varchar(255) NULL,
    middle_name varchar(255) NULL,
    sex varchar(6) NOT NULL,
    CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY ((ARRAY['male'::character varying, 'female'::character varying])::text[]))),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT fk_registred_address_id FOREIGN KEY (registred_address_id) REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id) REFERENCES address(id) ON UPDATE RESTRICT ON DELETE RESTRICT
);