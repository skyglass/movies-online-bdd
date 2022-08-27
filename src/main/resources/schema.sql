create table if not exists manufacturer (
    id identity,
    name nvarchar,
    short_name nvarchar(4),
    contact_number nvarchar(15),
    contact_name nvarchar
);

create table if not exists dg_product (
    id identity,
    album_title nvarchar(max),
    image_location nvarchar(max),
    price bigint not null,
    cost bigint not null,
    m_id bigint not null,
    descrip nvarchar(max)
);

alter table dg_product
    add foreign key (m_id) references manufacturer(id);

create table if not exists artist (
    id identity,
    name nvarchar(200)
);

create table if not exists prod_artists (
    product_id bigint not null,
    artist_id bigint not null
);

alter table prod_artists
    add foreign key (product_id) references dg_product(id);
alter table prod_artists
    add foreign key (artist_id) references artist(id);

create table if not exists product_group (
    id identity,
    name varchar(200)
);

create table if not exists product_group_products (
    product_group_id bigint not null,
    product_id bigint not null
);

alter table product_group_products
    add foreign key (product_id) references  dg_product(id);
alter table product_group_products
    add foreign key (product_group_id) references  product_group(id);

create table if not exists mrt_customer (
    id identity,
    username nvarchar,
    password nvarchar,
    email_address nvarchar,
    street_address_1 nvarchar not null,
    street_address_2 nvarchar,
    state_name nvarchar(50) not null,
    city nvarchar(200) not null,
    zip nvarchar(9) not null
);
alter table mrt_customer add constraint if not exists uniqueUsername unique(username);

create table if not exists mrt_location (
    id identity,
    street_address_1 nvarchar not null,
    street_address_2 nvarchar,
    state_name nvarchar(50) not null,
    city nvarchar(200) not null,
    zip nvarchar(9) not null
);

create index if not exists mrt_location_zip_idx on mrt_location(zip);

create table if not exists mrt_order (
    id identity,
    customer_code bigint,
    created_on datetime not null,
    billed_on datetime,
    store_id bigint,
    shipping_cost bigint not null,
    street_address_1 nvarchar not null,
    street_address_2 nvarchar,
    state_name nvarchar(50) not null,
    city nvarchar(200) not null,
    zip nvarchar(9) not null
);

alter table mrt_order
    add foreign key (customer_code) references mrt_customer(id);

alter table mrt_order
    add foreign key (store_id) references  mrt_location(id);

create table if not exists mrt_order_line_items (
    id identity,
    mrt_order_id bigint not null,
    charged_price bigint not null,
    product_id bigint not null
);

alter table mrt_order_line_items
    add foreign key (mrt_order_id) references mrt_order(id);

alter table mrt_order_line_items
    add foreign key (product_id) references dg_product(id);

create table if not exists cart (
    id identity,
    customer_code bigint,
    coupon_id bigint
);

create table if not exists cart_products (
    cart_id bigint,
    product_id bigint
);

alter table cart
    add foreign key (customer_code) references mrt_customer(id);
alter table cart_products
    add foreign key (product_id) references dg_product(id);
alter table cart_products
    add foreign key (cart_id) references cart(id) on delete cascade;

create table if not exists mrt_cust_loyalty_card (
    cust_code bigint primary key,
    purchase_count int,
    expiry_epoch datetime
);

alter table mrt_cust_loyalty_card
    add foreign key (cust_code) references mrt_customer(id);

create table if not exists coupon_code (
    id identity,
    code nvarchar(6) not null unique
);

alter table cart
    add foreign key (coupon_id) references coupon_code(id);