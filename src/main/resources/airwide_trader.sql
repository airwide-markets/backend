CREATE TABLE users (
  id bigint NOT NULL,
  email varchar(200) NOT NULL,
  password varchar(250) NOT NULL,
  name varchar(100) NOT NULL,
  surname varchar(100) NOT NULL,
  msisdn varchar(100) NOT NULL,
  town varchar(100) NOT NULL,
  country_code varchar(50) NOT NULL,
  country varchar(100) NOT NULL,
  address text NOT NULL,
  profile_image bigint NOT NULL,
  token varchar(100) NOT NULL,
  status varchar(1) -- enum('0','1','2','3') NOT NULL
);
-- ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE users ADD PRIMARY KEY (msisdn);
-- --------------------------------------------------------
ALTER TABLE users ADD column dial_code varchar(5);
ALTER TABLE users ADD column local_mobile_number varchar(20);

-- --------------------------------------------------------
DROP TABLE category;
CREATE TABLE category (
    id SERIAL NOT NULL,
    name varchar(100) NOT NULL,
    icon varchar(100) NOT NULL,
    request_type varchar(20) NOT NULL,
    listing_type varchar(20) NOT NULL
);
INSERT INTO category (name, icon, request_type, listing_type)
VALUES
    ('services','fa fa-cogs', 'buy', 'sell'),
    ('accommodation','fa fa-home', 'buy', 'sell'),
    ('accommodation','fa fa-home', 'rent', 'lease'),
    ('jobs','fa fa-briefcase', 'apply', 'offer'),
    ('vehicles','fa fa-car', 'buy', 'sell'),
    ('vehicles','fa fa-car', 'rent', 'lease'),
    ('products','fa fa-cogs', 'buy', 'sell'),
    ('machinery','fa fa-cogs', 'buy', 'sell'),
    ('machinery','fa fa-cogs', 'rent', 'lease');
-- --------------------------------------------------------
DROP TABLE higher_level_sub_category;
CREATE TABLE higher_level_sub_category (
  id BIGSERIAL NOT NULL,
  category_id int NOT NULL,
  name varchar(100 ) NOT NULL,
  listing_type varchar(20) NOT NULL,
  request_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE middle_level_sub_category;
CREATE TABLE middle_level_sub_category (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint NOT NULL,
  id BIGSERIAL NOT NULL,
  name varchar(100) NOT NULL,
  listing_type varchar(20) NOT NULL,
  request_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE lower_level_sub_category;
CREATE TABLE lower_level_sub_category (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint NOT NULL,
  middle_level_sub_category_id bigint NOT NULL,
  id BIGSERIAL NOT NULL,
  listing_type varchar(20) NOT NULL,
  request_type varchar(20) NOT NULL,
  name varchar(100) NOT NULL
);
-- ----------------------------------------------------------
DROP TABLE search_fields;
CREATE TABLE search_fields (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  id BIGSERIAL NOT NULL,
  name varchar(100) NOT NULL,
  listing_type varchar(20) NOT NULL,
  request_type varchar(20) NOT NULL
);
-- ----------------------------------------------------------
DROP TABLE orders;
CREATE TABLE orders (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  order_id BIGSERIAL NOT NULL,
  user_id bigint NOT NULL,
  description varchar(100) NOT NULL,
  narration text NOT NULL,
  order_date timestamp,
  available_date timestamp,
  order_type varchar(20) NOT NULL,
  photo_path varchar(255),
  country_id int
 -- ,
 -- PRIMARY KEY ( category_id,
 --               higher_level_sub_category_id,
 --               middle_level_sub_category_id,
 --               lower_level_sub_category_id,
 --               order_type,
 --               user_id )
);
-- --------------------------------------------------------
DROP TABLE photos;
CREATE TABLE photos (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint,
    id BIGSERIAL NOT NULL,
    photo_path VARCHAR(255)
);
-- --------------------------------------------------------
DROP TABLE service_orders;
CREATE TABLE service_orders (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  order_id bigint,
  order_detail_id BIGSERIAL NOT NULL,
  description varchar(100) NOT NULL,
  narration text NOT NULL);
  -- PRIMARY KEY ( category_id,
  --              higher_level_sub_category_id,
  --              middle_level_sub_category_id,
  --              lower_level_sub_category_id,
  --              order_id,
  --              order_detail_id ),
  -- FOREIGN KEY ( category_id,
  --              higher_level_sub_category_id,
  --              middle_level_sub_category_id,
  --              lower_level_sub_category_id,
  --              order_id )
  --   REFERENCES orders ( category_id,
  --                       higher_level_sub_category_id,
  --                       middle_level_sub_category_id,
  --                       lower_level_sub_category_id,
  --                       order_id )
-- );
-- --------------------------------------------------------
DROP TABLE product_orders;
CREATE TABLE product_orders (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  order_id bigint,
  order_detail_id BIGSERIAL NOT NULL,
  description varchar(100) NOT NULL,
  narration text NOT NULL);
-- --------------------------------------------------------
DROP TABLE machinery_orders;
CREATE TABLE machinery_orders (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  order_id bigint,
  order_detail_id BIGSERIAL NOT NULL,
  description varchar(100) NOT NULL,
  narration text NOT NULL);
-- --------------------------------------------------------
DROP TABLE vehicle_orders;
CREATE TABLE vehicle_orders (
  category_id int NOT NULL,
  higher_level_sub_category_id bigint DEFAULT NULL,
  middle_level_sub_category_id bigint DEFAULT NULL,
  lower_level_sub_category_id bigint DEFAULT NULL,
  order_id bigint,
  order_detail_id BIGSERIAL NOT NULL,
  description varchar(100) NOT NULL,
  narration text NOT NULL,
  country_id int);
-- --------------------------------------------------------
DROP TABLE products;
CREATE TABLE products (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint,
    id BIGSERIAL NOT NULL,
    description varchar(100) NOT NULL,
    narration text NOT NULL,
    order_date timestamp NOT NULL,
    order_type varchar(20) NOT NULL
);

-- --------------------------------------------------------
DROP TABLE machinery;
CREATE TABLE machinery (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint,
    id BIGSERIAL NOT NULL,
    description varchar(100) NOT NULL,
    narration text NOT NULL,
    order_date timestamp NOT NULL,
    order_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE vehicles;
CREATE TABLE vehicles (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint,
    id BIGSERIAL NOT NULL,
    description varchar(100) NOT NULL,
    narration text NOT NULL,
    thumbnail varchar(200) NOT NULL,
    price decimal(20,2) NOT NULL,
    country_id int NOT NULL,
    state_id bigint NOT NULL,
    city_id bigint NOT NULL,
    order_date timestamp NOT NULL,
    order_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE accommodation;
CREATE TABLE accommodation (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint NOT NULL,
    id BIGSERIAL NOT NULL,
    description varchar(200) NOT NULL,
    thumbnail varchar(200) NOT NULL,
    no_of_bedrooms int NOT NULL,
    price decimal(20,2) NOT NULL,
    country_id int NOT NULL,
    state_id bigint NOT NULL,
    city_id bigint NOT NULL,
    date_vacant timestamp NOT NULL,
    order_date timestamp NOT NULL,
    order_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE jobs;
CREATE TABLE jobs (
    category_id int NOT NULL,
    higher_level_sub_category_id bigint DEFAULT NULL,
    middle_level_sub_category_id bigint DEFAULT NULL,
    lower_level_sub_category_id bigint DEFAULT NULL,
    order_id bigint NOT NULL,
    id BIGSERIAL NOT NULL,
    description varchar(200) NOT NULL,
    job_level int NOT NULL,
    qualification int NOT NULL,
    deadline timestamp NOT NULL,
    narration text NOT NULL,
    country_id int NOT NULL,
    state_id bigint NOT NULL,
    city_id bigint NOT NULL,
    order_date timestamp NOT NULL,
    order_type varchar(20) NOT NULL
);
-- --------------------------------------------------------
DROP TABLE countries;
CREATE TABLE countries (
  country_id int NOT NULL,
  country_name varchar(60) NOT NULL,
  status int NOT NULL DEFAULT '1' -- 0-Blocked, 1-Active
);
ALTER TABLE countries ADD PRIMARY KEY (country_id);
-- --------------------------------------------------------
DROP TABLE states;
CREATE TABLE states (
  country_id int NOT NULL,
  state_id int NOT NULL,
  state_name varchar(60) NOT NULL ,
  status int NOT NULL DEFAULT 1 -- 0:Blocked, 1:Active
);
ALTER TABLE states ADD PRIMARY KEY (country_id, state_id);
-- ----------------------------------------------------------
DROP TABLE cities;
CREATE TABLE cities (
  state_id int NOT NULL,
  city_id int NOT NULL,
  city_name varchar(60) NOT NULL,
  status int NOT NULL DEFAULT 1  -- '0:Blocked, 1:Active'
);
ALTER TABLE cities ADD PRIMARY KEY (state_id, city_id);
-- ----------------------------------------------------------