insert into manufacturer (name, short_name, contact_number, contact_name) values ('Quality', 'QR', '8565554255', 'J. Mallard');
select @quality_records := 1;
insert into manufacturer (name, short_name, contact_number, contact_name) values ('SpinFast Records', 'SFR', '+44 1632 960609', 'Lawrence Hendricks II');
select @spinfast := 2;
insert into manufacturer (name, short_name, contact_number, contact_name) values ('Argentina', 'A', '2125553695', 'Jamie Weston');
select @argentina := 3;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('The coworker', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9434, 4300, @quality_records, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Amet volutpat consequat mauris nunc congue nisi vitae suscipit. Aliquet bibendum enim facilisis gravida neque convallis a. Amet consectetur adipiscing elit duis tristique sollicitudin. Vulputate odio ut enim blandit volutpat maecenas volutpat. Amet nisl purus in mollis nunc sed. Fermentum et sollicitudin ac orci phasellus egestas tellus rutrum tellus. Vulputate eu scelerisque felis imperdiet proin fermentum leo vel. Phasellus vestibulum lorem sed risus ultricies tristique nulla. Duis convallis convallis tellus id interdum velit laoreet. Maecenas accumsan lacus vel facilisis.');
select @up_street := 1;
insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Hand me housing', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5936, 2400, @quality_records, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dis parturient montes nascetur ridiculus mus mauris vitae ultricies leo. Posuere ac ut consequat semper. Molestie ac feugiat sed lectus vestibulum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Potenti nullam ac tortor vitae purus faucibus. Sapien et ligula ullamcorper malesuada proin libero nunc. Odio eu feugiat pretium nibh ipsum consequat nisl. Arcu vitae elementum curabitur vitae. Vel pharetra vel turpis nunc eget lorem dolor sed. Sit amet risus nullam eget felis eget nunc. A erat nam at lectus urna duis convallis.');
select @last_one := 2;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Downtown boy', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7806, 5000, @quality_records, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nibh nisl condimentum id venenatis a condimentum vitae. Neque laoreet suspendisse interdum consectetur libero id faucibus nisl tincidunt. Integer feugiat scelerisque varius morbi enim nunc faucibus a. Platea dictumst vestibulum rhoncus est. Dictum sit amet justo donec enim. Etiam non quam lacus suspendisse. Ut tellus elementum sagittis vitae et leo duis ut. In fermentum et sollicitudin ac orci phasellus. Volutpat lacus laoreet non curabitur gravida arcu. Arcu cursus vitae congue mauris rhoncus aenean vel. In massa tempor nec feugiat nisl. Urna id volutpat lacus laoreet non curabitur gravida arcu. Justo laoreet sit amet cursus sit amet dictum.');
select @downtown_boy := 3;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('clean fingers', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 824, 10, @argentina, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Blandit massa enim nec dui nunc mattis. Nunc sed velit dignissim sodales ut. Eu lobortis elementum nibh tellus molestie nunc. Velit egestas dui id ornare arcu odio ut. Id neque aliquam vestibulum morbi blandit. In nisl nisi scelerisque eu ultrices vitae auctor. Mauris pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Curabitur gravida arcu ac tortor dignissim convallis aenean et. Tortor id aliquet lectus proin nibh nisl condimentum id. Non enim praesent elementum facilisis leo vel. Faucibus ornare suspendisse sed nisi lacus. Maecenas accumsan lacus vel facilisis volutpat est velit. Vitae proin sagittis nisl rhoncus mattis rhoncus urna neque.');
select @clean_fingers := 4;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('aftergym', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2566, 340, @quality_records, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ultricies tristique nulla aliquet enim tortor at auctor urna. Nulla malesuada pellentesque elit eget gravida. Placerat duis ultricies lacus sed turpis tincidunt id aliquet. Aliquam vestibulum morbi blandit cursus risus at ultrices. Mi tempus imperdiet nulla malesuada pellentesque elit eget. Viverra orci sagittis eu volutpat odio facilisis mauris. Velit laoreet id donec ultrices tincidunt arcu non. Pellentesque adipiscing commodo elit at imperdiet dui accumsan. Non nisi est sit amet facilisis magna etiam. Nisi est sit amet facilisis magna etiam. Cras ornare arcu dui vivamus. Dis parturient montes nascetur ridiculus mus mauris. Ut tristique et egestas quis. Mauris cursus mattis molestie a.');
select @aftergym := 5;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Col. Squash''s Perfectly Content Band', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2340, 16, @argentina, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dui id ornare arcu odio. Nulla facilisi morbi tempus iaculis urna id volutpat. Id aliquet lectus proin nibh nisl condimentum id. Nisi lacus sed viverra tellus in hac habitasse platea dictumst. Nunc sed id semper risus in hendrerit. Amet porttitor eget dolor morbi non arcu risus quis varius. Eleifend donec pretium vulputate sapien. Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus. Lectus quam id leo in vitae turpis massa sed. In vitae turpis massa sed elementum tempus egestas. Pellentesque dignissim enim sit amet venenatis urna cursus. Maecenas pharetra convallis posuere morbi leo.');
select @squash := 6;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('A normal day''s evening', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2940, 300, @quality_records, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Consectetur a erat nam at lectus. Nulla pellentesque dignissim enim sit amet venenatis. Egestas quis ipsum suspendisse ultrices gravida dictum. Quisque sagittis purus sit amet volutpat. Donec ac odio tempor orci dapibus. Sed velit dignissim sodales ut eu sem integer vitae. Dui sapien eget mi proin sed libero. Vivamus arcu felis bibendum ut. Non tellus orci ac auctor augue mauris. Maecenas ultricies mi eget mauris. Egestas purus viverra accumsan in nisl nisi scelerisque. Nibh ipsum consequat nisl vel pretium lectus quam. Pretium fusce id velit ut tortor pretium viverra suspendisse potenti. Urna cursus eget nunc scelerisque viverra mauris.');
select @evening := 7;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Candle stick maker street', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 3400, 800, @argentina, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. A scelerisque purus semper eget duis at. Et malesuada fames ac turpis. Viverra accumsan in nisl nisi scelerisque eu ultrices vitae. Blandit turpis cursus in hac. Ipsum consequat nisl vel pretium lectus quam id leo in. Eget arcu dictum varius duis. Nunc lobortis mattis aliquam faucibus purus in massa tempor. Elit duis tristique sollicitudin nibh sit amet commodo nulla. Enim ut tellus elementum sagittis vitae et leo. Proin sed libero enim sed faucibus turpis in eu mi. Egestas quis ipsum suspendisse ultrices gravida. Nec sagittis aliquam malesuada bibendum arcu vitae elementum curabitur. Platea dictumst vestibulum rhoncus est pellentesque. Elit eget gravida cum sociis natoque penatibus et. Quis commodo odio aenean sed adipiscing diam donec. Ut aliquam purus sit amet luctus venenatis lectus. Ac feugiat sed lectus vestibulum. Non consectetur a erat nam at lectus. Amet luctus venenatis lectus magna fringilla urna.');
select @candle := 8;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('The Rude', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 9468, 4500, @argentina, '');
select @the_rude := 9;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Sitting on the Ground', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4708, 3000, @argentina, '');
select @ground := 10;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Abstract Graffiti', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 2662, 200, @spinfast, '');
select @abstract_graffiti := 11;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('The Song Has Changed', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 8338, 2512, @argentina, '');
select @song_has_changed := 12;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Blimps 101', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4330, 3000, @spinfast, '');
select @blimps_101 := 13;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Builder', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 5792, 1600, @argentina, '');
select @builder := 14;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('People of the day', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 4678, 300, @spinfast, '');
select @people_of_the_day := 15;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Normal Sunsets', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 7408, 2543, @spinfast, '');
select @normal_sunsets := 16;

insert into dg_product (album_title, image_location, price, cost, m_id, descrip) values ('Cold in The Sun', 'https://a.1stdibscdn.com/a_3543/a_63360221592442733155/IMG_1176_master.jpg?disable=upscale&auto=webp&quality=60&width=960', 6382, 1800, @argentina, '');
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

-- pass is 'wibble'
merge into mrt_customer
    key(id)
    values (1, 'local', '$2a$10$6VaNwr8M.bKPTql0V2VD5uYRpsrH7Ch9INsuekJHDK5hJVDvb7eUW', '', '1120 N Ashland Ave', '', 'Chicago', 'Illinois', '60622');

-- pass is 'gold'
merge into mrt_customer
    key(id)
    values (2, 'mellowsilver', '$2a$10$DHw6NLfwiIAaq39EeA5VEujrqTIykNGOuCI5jEMXH47xjCaz7Rste', '', '147 Grand St', '', 'Brooklyn', 'New York', '11249');

-- pass is 'lessmoney'
merge into mrt_customer
    key(id)
    values (3, 'multiplestores', '$2a$10$uA17UXE6DyV/FR0oufbY8uYVH1ZFjxuxbgPqbQCDLs8tgzAnkTm02', '', '147 Grand St', '', 'Brooklyn', 'New York', '11249');

-- pass is 'momoney'
merge into mrt_customer
    key(id)
    values (4, 'onestore', '$2a$10$M.vRKKBKpFxSFzedNcsYGOm6cDCYabljxs1smaDAnIuBgON5m8qaG', '', '86 2nd St', '', 'Tulsa', 'Oklahoma', '74145');

-- pass is 'super'
merge into mrt_customer
    key(id)
    values (5, 'admin', '$2a$10$1flzWDVVKHt9brSvFOpsTO2/.ryl.5NPDzQL2XbdiPXuquoZHnabC', '', '1120 N Ashland Ave', '', 'Chicago', 'Illinois', '60622');

merge into mrt_location
    key(id)
    values (15, '4741 Camel Back Road', 'corporate office', 'Tulsa', 'Oklahoma', '74145');

merge into mrt_location
    key(id)
    values (321, '81 Union St', 'brooklyn office', 'Brooklyn', 'New York', '11249');

merge into mrt_location
    key(id)
    values (16, '18 Lafayette Blvd', 'brooklyn store', 'Brooklyn', 'New York', '11249');

merge into mrt_location
    key(id)
    values (122, '41 Graedy Blvd', 'brooklyn store', 'Brooklyn', 'New York', '11249');

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