
CREATE SEQUENCE sequence_generator START WITH 1 INCREMENT BY 1;

-- public.announcement definition

-- Drop table

-- DROP TABLE public.customers;

CREATE TABLE public.customers (
	id int8 NOT NULL,
	email_office varchar(255) NOT NULL,
	email_personal varchar(255) NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	CONSTRAINT customers_pkey PRIMARY KEY (id)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	id int8 NOT NULL,
	item_type varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	price float8 NOT NULL,
	quantity int4 NOT NULL,
	owner_id int8 NOT NULL,
	CONSTRAINT products_pkey PRIMARY KEY (id)
);


-- public.products foreign keys

ALTER TABLE public.products ADD CONSTRAINT fkro44r59v36geu45odgaqnx17v FOREIGN KEY (owner_id) REFERENCES public.customers(id);


-- public.family_members definition

-- Drop table

-- DROP TABLE public.family_members;

CREATE TABLE public.family_members (
	id int8 NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	customer_id int8 NULL,
	CONSTRAINT family_members_pkey PRIMARY KEY (id)
);


-- public.family_members foreign keys

ALTER TABLE public.family_members ADD CONSTRAINT fkqv9kvcw8ue36f9v4tb2wf9rw4 FOREIGN KEY (customer_id) REFERENCES public.customers(id);