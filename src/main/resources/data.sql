insert into dg_product (album_title, image_location) values ('The coworker', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @up_street := 1;
insert into dg_product (album_title, image_location) values ('Hand me housing', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @last_one := 2;

insert into dg_product (album_title, image_location) values ('Downtown boy', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @downtown_boy := 3;

insert into dg_product (album_title, image_location) values ('clean fingers', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @clean_fingers := 4;

insert into dg_product (album_title, image_location) values ('aftergym', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @aftergym := 5;

insert into dg_product (album_title, image_location) values ('Col. Squash''s Perfectly Content Band', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @squash := 6;

insert into dg_product (album_title, image_location) values ('A normal day''s evening', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @evening := 7;

insert into dg_product (album_title, image_location) values ('Candle stick maker street', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @candle := 8;

insert into dg_product (album_title, image_location) values ('The Rude', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @the_rude := 9;

insert into dg_product (album_title, image_location) values ('Sitting on the Ground', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @ground := 10;

insert into dg_product (album_title, image_location) values ('Abstract Graffiti', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @abstract_graffiti := 11;

insert into dg_product (album_title, image_location) values ('The Song Has Changed', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @song_has_changed := 12;

insert into dg_product (album_title, image_location) values ('Blimps 101', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @blimps_101 := 13;

insert into dg_product (album_title, image_location) values ('Builder', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @builder := 14;

insert into dg_product (album_title, image_location) values ('People of the day', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @people_of_the_day := 15;

insert into dg_product (album_title, image_location) values ('Normal Sunsets', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
select @normal_sunsets := 16;

insert into dg_product (album_title, image_location) values ('Cold in The Sun', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960');
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

insert into product_group () values ();

insert into product_group_products (product_group_id, product_id) values (1, 3);
insert into product_group_products (product_group_id, product_id) values (1, 5);
insert into product_group_products (product_group_id, product_id) values (1, 2);
insert into product_group_products (product_group_id, product_id) values (1, 1);