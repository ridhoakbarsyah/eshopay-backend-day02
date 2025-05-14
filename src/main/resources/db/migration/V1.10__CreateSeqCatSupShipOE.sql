CREATE SEQUENCE IF NOT EXISTS oe.suppliers_supplier_id_seq
    START WITH 30
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS oe.categories_category_id_seq
    START WITH 9
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS oe.shippers_shipper_id_seq
    START WITH 7
    INCREMENT BY 1;

-- Alter table oe.categories --
ALTER TABLE oe.categories
ALTER COLUMN category_id SET DEFAULT nextval('oe.categories_category_id_seq');

-- Make category_id the primary key
ALTER TABLE oe.categories
ADD CONSTRAINT categories_pk PRIMARY KEY (category_id);

-- Alter table oe.shippers --
ALTER TABLE oe.shippers
ALTER COLUMN shipper_id SET DEFAULT nextval('oe.shippers_shipper_id_seq');

-- Alter table oe.suppliers --
ALTER TABLE oe.suppliers
ALTER COLUMN supplier_id SET DEFAULT nextval('oe.suppliers_supplier_id_seq');

-- Make supplier_id the primary key
ALTER TABLE oe.suppliers
ADD CONSTRAINT suppliers_pk PRIMARY KEY (supplier_id);