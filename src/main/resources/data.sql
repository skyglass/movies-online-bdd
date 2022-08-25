insert into dg_product (album_title, image_location, price) values ('The coworker', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9434);
select @up_street := 1;
insert into dg_product (album_title, image_location, price) values ('Hand me housing', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5936);
select @last_one := 2;

insert into dg_product (album_title, image_location, price) values ('Downtown boy', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7806);
select @downtown_boy := 3;

insert into dg_product (album_title, image_location, price) values ('clean fingers', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 824);
select @clean_fingers := 4;

insert into dg_product (album_title, image_location, price) values ('aftergym', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2566);
select @aftergym := 5;

insert into dg_product (album_title, image_location, price) values ('Col. Squash''s Perfectly Content Band', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2340);
select @squash := 6;

insert into dg_product (album_title, image_location, price) values ('A normal day''s evening', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2940);
select @evening := 7;

insert into dg_product (album_title, image_location, price) values ('Candle stick maker street', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 3400);
select @candle := 8;

insert into dg_product (album_title, image_location, price) values ('The Rude', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9468);
select @the_rude := 9;

insert into dg_product (album_title, image_location, price) values ('Sitting on the Ground', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4708);
select @ground := 10;

insert into dg_product (album_title, image_location, price) values ('Abstract Graffiti', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2662);
select @abstract_graffiti := 11;

insert into dg_product (album_title, image_location, price) values ('The Song Has Changed', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 8338);
select @song_has_changed := 12;

insert into dg_product (album_title, image_location, price) values ('Blimps 101', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4330);
select @blimps_101 := 13;

insert into dg_product (album_title, image_location, price) values ('Builder', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5792);
select @builder := 14;

insert into dg_product (album_title, image_location, price) values ('People of the day', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4678);
select @people_of_the_day := 15;

insert into dg_product (album_title, image_location, price) values ('Normal Sunsets', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7408);
select @normal_sunsets := 16;

insert into dg_product (album_title, image_location, price) values ('Cold in The Sun', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 6382);
select @cold_in_the_sun := 17;

insert into artist (name) values ('Bobby Joel');
select @bobby_joel := 1;

insert into artist (name) values ('The Bugs');
select @the_bugs := 2;

insert into artist (name) values ('The stationary boulders');
select @stationary_boulders := 3;

insert into artist (name) values ('Harry Graffiti');
select @harry_graffiti := 4;

insert into artist (name) values ('Cameron Tones');
select @cameron_tones := 5;

insert into artist (name) values ('Blimps');
select @blimps := 6;

insert into artist (name) values ('Embrace');
select @embrace := 7;

insert into prod_artists (product_id, artist_id) values (@up_street, @bobby_joel);
insert into prod_artists (product_id, artist_id) values (@downtown_boy, @bobby_joel);
insert into prod_artists (product_id, artist_id) values (@last_one, @stationary_boulders);
insert into prod_artists (product_id, artist_id) values (@clean_fingers, @stationary_boulders);
insert into prod_artists (product_id, artist_id) values (@aftergym, @stationary_boulders);
insert into prod_artists (product_id, artist_id) values (@squash, @the_bugs);
insert into prod_artists (product_id, artist_id) values (@evening, @the_bugs);
insert into prod_artists (product_id, artist_id) values (@candle, @harry_graffiti);
insert into prod_artists (product_id, artist_id) values (@the_rude, @cameron_tones);
insert into prod_artists (product_id, artist_id) values (@ground, @cameron_tones);
insert into prod_artists (product_id, artist_id) values (@abstract_graffiti, @blimps);
insert into prod_artists (product_id, artist_id) values (@song_has_changed, @blimps);
insert into prod_artists (product_id, artist_id) values (@blimps_101, @blimps);
insert into prod_artists (product_id, artist_id) values (@builder, @embrace);
insert into prod_artists (product_id, artist_id) values (@people_of_the_day, @embrace);
insert into prod_artists (product_id, artist_id) values (@normal_sunsets, @embrace);
insert into prod_artists (product_id, artist_id) values (@cold_in_the_sun, @embrace);

insert into product_group (name) values ('flash sale');

insert into product_group_products (product_group_id, product_id) values (1, 3);
insert into product_group_products (product_group_id, product_id) values (1, 5);
insert into product_group_products (product_group_id, product_id) values (1, 2);
insert into product_group_products (product_group_id, product_id) values (1, 1);

insert into mrt_customer (username, street_address_1, city, state_name, zip) values ('local', '1120 N Ashland Ave', 'Chicago', 'Illinois', '60622');
insert into mrt_customer (username, street_address_1, street_address_2, city, state_name, zip) values ('manyStores', '440 W 41st St', '10B', 'New York', 'New York', '10036');

insert into mrt_location (street_address_1, street_address_2, state_name, city, zip) values ('440 W 41st St', '13 F', 'New York', 'New York', '10036');
insert into mrt_location (street_address_1, street_address_2, state_name, city, zip) values ('628 10th Ave', '3 Q', 'New York', 'New York', '10036');
insert into mrt_location (street_address_1, street_address_2, state_name, city, zip) values ('432 W 45th St', '201', 'New York', 'New York', '10036');

insert into mrt_customer (username, street_address_1, street_address_2, city, state_name, zip) values ('oneStore', '440 W 41st St', '10B', 'New York', 'New York', '74145');

merge into mrt_location
    key(id)
    values (122, '4741 Camel Back Road', 'corporate office', 'Tulsa', 'Oklahoma', '74145');

merge into coupon_code
    key(code)
    values (1, 'FN2187');