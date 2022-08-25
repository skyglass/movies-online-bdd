insert into manufacturer (name, short_name, contact_number, contact_name) values ('Quality', 'QR', '8565554255', 'J. Mallard');
select @quality_records := 1;
insert into manufacturer (name, short_name, contact_number, contact_name) values ('SpinFast Records', 'SFR', '+44 1632 960609', 'Lawrence Hendricks II');
select @spinfast := 2;
insert into manufacturer (name, short_name, contact_number, contact_name) values ('Argentina', 'A', '2125553695', 'Jamie Weston');
select @argentina := 3;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('The coworker', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9434, 4300, @quality_records);
select @up_street := 1;
insert into dg_product (album_title, image_location, price, cost, m_id) values ('Hand me housing', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5936, 2400, @quality_records);
select @last_one := 2;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Downtown boy', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7806, 5000, @quality_records);
select @downtown_boy := 3;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('clean fingers', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 824, 10, @argentina);
select @clean_fingers := 4;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('aftergym', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2566, 340, @quality_records);
select @aftergym := 5;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Col. Squash''s Perfectly Content Band', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2340, 16, @argentina);
select @squash := 6;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('A normal day''s evening', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2940, 300, @quality_records);
select @evening := 7;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Candle stick maker street', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 3400, 800, @argentina);
select @candle := 8;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('The Rude', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9468, 2000, @argentina);
select @the_rude := 9;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Sitting on the Ground', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4708, 3000, @argentina);
select @ground := 10;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Abstract Graffiti', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2662, 200, @spinfast);
select @abstract_graffiti := 11;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('The Song Has Changed', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 8338, 2512, @argentina);
select @song_has_changed := 12;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Blimps 101', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4330, 3000, @spinfast);
select @blimps_101 := 13;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Builder', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5792, 1600, @argentina);
select @builder := 14;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('People of the day', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4678, 300, @spinfast);
select @people_of_the_day := 15;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Normal Sunsets', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7408, 2543, @spinfast);
select @normal_sunsets := 16;

insert into dg_product (album_title, image_location, price, cost, m_id) values ('Cold in The Sun', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 6382, 1800, @argentina);
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
insert into mrt_customer (username, street_address_1, city, state_name, zip) values ('noStores', '147 Union St', 'Atlanta', 'Georgia', '45689');
insert into mrt_customer (username, street_address_1, city, state_name, zip) values ('twoStores', '147 Grand St', 'Brooklyn', 'New York', '11249');

insert into mrt_customer (username, street_address_1, street_address_2, city, state_name, zip) values ('oneStore', '40 W Main St', '10B', 'Tulsa', 'Oklahoma', '74145');

merge into mrt_location
    key(id)
    values (15, '4741 Camel Back Road', 'corporate office', 'Tulsa', 'Oklahoma', '74145');

merge into mrt_location
    key(id)
    values (321, '81 Union St', 'brooklyn office', 'Brooklyn', 'New York', '11249');

merge into mrt_location
    key(id)
    values (122, '18 Lafayette Blvd', 'brooklyn store', 'Brooklyn', 'New York', '11249');


merge into mrt_location
    key(id)
    values (122, '81 Union St', 'brooklyn office', 'Brooklyn', 'New York', '11249');

merge into mrt_location
    key(id)
    values (122, '18 Lafayette Blvd', 'brooklyn store', 'Brooklyn', 'New York', '11249');

insert into mrt_order (customer_code, created_on, billed_on, store_id, shipping_cost, street_address_1, street_address_2, state_name, city, zip)
values
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622');

insert into mrt_order (customer_code, created_on, billed_on, store_id, shipping_cost, street_address_1, street_address_2, state_name, city, zip)
values
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '2' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622');


insert into mrt_order (customer_code, created_on, billed_on, store_id, shipping_cost, street_address_1, street_address_2, state_name, city, zip)
values
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622'),
(2, CURRENT_DATE - INTERVAL '4' MONTH, NULL, 122, 2667, '1120 N Ashland Ave', NULL, 'Illinois', 'Chicago', '60622');

insert into mrt_order_line_items (mrt_order_id, charged_price, product_id)
values
(1, 7806, 3),
(2, 6382, 17),
(3, 5792, 14),
(3, 5792, 14),
(3, 5792, 14),
(3, 5792, 14),
(3, 5792, 14),
(4, 3400, 8),
(4, 3400, 8),
(5, 6382, 17),
(5, 6382, 17),
(5, 6382, 17),
(5, 6382, 17),
(5, 6382, 17),
(5, 6382, 17),
(6, 7806, 3),
(7, 9434, 1),
(8, 7806, 3),
(8, 6382, 17),
(9, 5792, 14),
(9, 5792, 14),
(10, 5792, 14),
(10, 5792, 14),
(12, 5792, 14),
(12, 3400, 8),
(12, 3400, 8),
(13, 6382, 17),
(14, 7806, 3),
(15, 9434, 1),
(16, 7806, 3),
(16, 6382, 17),
(17, 5792, 14),
(15, 5792, 14),
(17, 5792, 14),
(17, 5792, 14),
(13, 5792, 14),
(13, 3400, 8),
(13, 3400, 8);

merge into mrt_cust_loyalty_card
    key(cust_code)
    values (1, 0, TIMESTAMP '2022-01-19 00:00:00');

merge into coupon_code
    key(code)
    values (1, 'FN2187');