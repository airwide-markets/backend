INSERT INTO categories (name, description, icon) VALUES
('Services', '', 'fa fa-cogs'),
('Accommodation', '', 'fa fa-home'),
('Jobs', '', 'fa fa-briefcase'),
('Vehicle', '', 'fa fa-car');
-- --------------------------------------------------------
CREATE SEQUENCE product_class_id_seq;
CREATE TABLE product_class (
    id int NOT NULL DEFAULT nextval('product_class_id_seq'),
    category_id int,
    name varchar(100) NOT NULL
);
INSERT INTO product_class (category_id, name)
VALUES(1, 'Generic');
-- --------------------------------------------------------
CREATE SEQUENCE product_search_id_seq;
CREATE TABLE product_search_field (
    id bigint NOT NULL DEFAULT nextval('product_search_id_seq'),
    category_id int,
    product_class_id bigint,
    name varchar(100) NOT NULL
);



