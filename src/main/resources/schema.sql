create table if not exists dg_product (
    id identity,
    album_title nvarchar(max),
    image_location nvarchar(max)
);

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
    id identity
);

create table if not exists product_group_products (
    product_group_id bigint not null,
    product_id bigint not null
);

alter table product_group_products
    add foreign key (product_id) references  dg_product(id);
alter table product_group_products
    add foreign key (product_group_id) references  product_group(id);