-- Insert data into the categories table for music-related products
INSERT INTO categories (name, description) VALUES
('Music Albums', 'Physical or digital albums containing a collection of songs'),
('Instruments', 'Musical instruments for playing and composing music'),
('Vinyl Records', 'Vinyl discs for playing music, commonly used for vintage or collector items'),
('Music Accessories', 'Accessories for music players, such as headphones, speakers, etc.'),
('Music Equipment', 'Sound systems and equipment for music production and performances');

-- Insert products into the 'products' table for the 'Music Albums' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at, brand) VALUES
('Greatest Hits', 'Compilation album', 'A collection of the best songs from various albums by the artist.', 1, 15.99, 10, true, 100, 10.00, 12, 12, 0.5, 0.2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Universal Music'),
('Live Concert Performance', 'Live album recording', 'A live performance of popular songs from the artist recorded on stage.', 1, 19.99, 15, true, 50, 12.00, 12, 12, 0.5, 0.25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Sony Music'),
('Acoustic Sessions', 'Unplugged album', 'An acoustic version of famous tracks performed in a studio setting.', 1, 12.99, 20, true, 75, 8.00, 12, 12, 0.5, 0.22, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Warner Music');

-- Insert products into the 'products' table for the 'Instruments' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at, brand) VALUES
('Electric Guitar', 'String instrument', 'A solid body electric guitar perfect for rock music.', 2, 299.99, 5, true, 30, 150.00, 100, 35, 7, 3.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Fender'),
('Acoustic Guitar', 'String instrument', 'A classic acoustic guitar for beginners and professionals alike.', 2, 199.99, 10, true, 50, 120.00, 105, 40, 12, 2.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Yamaha'),
('Drum Set', 'Percussion instrument', 'A full drum set with cymbals, perfect for creating rhythm.', 2, 499.99, 5, true, 20, 350.00, 120, 120, 80, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Pearl');

-- Insert products into the 'products' table for the 'Vinyl Records' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at, brand) VALUES
('Classic Rock Vinyl', 'Vinyl record', 'A vinyl record featuring classic rock songs.', 3, 25.99, 5, true, 40, 15.00, 12, 12, 0.3, 0.25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'EMI'),
('Jazz Collection Vinyl', 'Vinyl record', 'A vinyl collection of the best jazz music performances.', 3, 29.99, 10, true, 35, 18.00, 12, 12, 0.3, 0.27, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Blue Note Records'),
('Vinyl Collection: 80s Hits', 'Vinyl record', 'A collection of 80s hits on vinyl.', 3, 22.99, 8, true, 60, 13.00, 12, 12, 0.3, 0.24, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Rhino Records');

-- Insert products into the 'products' table for the 'Music Accessories' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at, brand) VALUES
('Noise Cancelling Headphones', 'Headphones', 'High-quality headphones with active noise cancellation for an immersive listening experience.', 4, 89.99, 15, true, 100, 50.00, 20, 15, 8, 0.6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bose'),
('Portable Bluetooth Speaker', 'Speaker', 'A small portable speaker with Bluetooth connectivity for music on the go.', 4, 49.99, 10, true, 150, 30.00, 10, 10, 8, 0.8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'JBL'),
('Guitar Picks (Set of 10)', 'Accessory', 'A set of 10 durable guitar picks for different playing styles.', 4, 9.99, 0, true, 200, 2.00, 5, 5, 0.2, 0.05, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Dunlop');

-- Insert products into the 'products' table for the 'Music Equipment' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at, brand) VALUES
('DJ Mixer', 'Music Equipment', 'A professional DJ mixer for mixing tracks at live events.', 5, 299.99, 5, true, 25, 180.00, 40, 30, 12, 4.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Pioneer DJ'),
('Microphone', 'Music Equipment', 'A high-quality microphone for studio and live performances.', 5, 79.99, 10, true, 100, 40.00, 25, 5, 5, 0.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Shure'),
('Sound Amplifier', 'Music Equipment', 'A powerful sound amplifier for high-volume performances and studio work.', 5, 199.99, 8, true, 40, 120.00, 30, 25, 15, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Behringer');

-- Insert data into the 'images' table

INSERT INTO images (url, description, is_principal, product_id)
VALUES
-- Principal image for product with ID 1
    ('https://upload.wikimedia.org/wikipedia/pt/f/fb/Beatles-one.jpg', 'Main image for Product 1', true, 1),
    ('https://m.media-amazon.com/images/M/MV5BYmYyMzQzODUtYTc2NC00MWU4LWEzNTYtNTk4Yjk0MTA0M2M1XkEyXkFqcGc@._V1_.jpg', 'Optional image 1 for Product 1', false, 1),
    ('https://images-americanas.b2w.io/produtos/7390019/imagens/cd-queen-greatest-hits-ii/7390019_1_xlarge.jpg', 'Optional image 2 for Product 1', false, 1),
-- Principal image for product with ID 2
    ('https://i0.wp.com/www.wikimetal.com.br/wp-content/uploads/2018/09/Queen-Live-Aid.jpg?fit=696%2C406&ssl=1', 'Main image for Product 2', true, 2),
    ('https://cdn.prod.website-files.com/655e0fa544c67c1ee5ce01c7/655e0fa544c67c1ee5ce11c2_live-performance-tips-to-help-you-put-on-a-great-show-header.jpeg', 'Optional image for Product 2', false, 2),
-- Principal image for product with ID 3
    ('https://i.scdn.co/image/ab67616d0000b273f31a4cd4fcf00749d0d49422', 'Main image for Product 3', true, 3),
    ('https://i.ytimg.com/vi/ogguLW6n6VI/maxresdefault.jpg', 'Optional image 1 for Product 3', false, 3),
    ('https://guitar.com/wp-content/uploads/2018/02/how-to-buy-acoustic-guitar-scaled.jpg', 'Optional image 2 for Product 3', false, 3),
-- Principal image for product with ID 4
    ('https://aimm.edu/hubfs/rock-guitar-genre.jpg', 'Main image for Product 4', true, 4),
    ('https://media.sweetwater.com/m/insync/2022/06/Featured-Guitar-9-Exceptional-Electric-Guitars.jpg', 'Optional image for Product 4', false, 4),
-- Principal image for product with ID 5
    ('https://collingsguitars.com/wp-content/uploads/2023/12/hero1-aspect-ratio-2-1-1024x512.jpg', 'Main image for Product 5', true, 5),
-- Principal image for product with ID 6
    ('https://www.drumazon.com/cdn/shop/files/YAMAHA-STAGE-CUSTOM-8-PIECE-DRUM-KIT-MATTE-SURF-GREEN-DRUMAZON_02_1000x.jpg?v=1705429570', 'Main image for Product 6', true, 6),
-- Principal image for product with ID 7
    ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTir4Ywy2Vgx_2DPsCI_Hd_R5WAb7n4nzSSQ&s', 'Main image for Product 7', true, 7),
-- Principal image for product with ID 8
    ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJFebxlHw-LsFkg2TQcs6PwAh12mMSWxxG2A&s', 'Main image for Product 8', true, 8),
-- Principal image for product with ID 9
    ('https://m.media-amazon.com/images/I/71cc++bXDiL._UF350,350_QL50_.jpg', 'Main image for Product 9', true, 9),
-- Principal image for product with ID 10
    ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5ZCsdfdLaKWWW8kJX2LQ2RpQbm7UiBqQ9kQ&s', 'Main image for Product 10', true, 10),
-- Principal image for product with ID 11
    ('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYhuVaea1fklHu8YrI4eaa9QD2wOiMDttrZA&s', 'Main image for Product 11', true, 11),
-- Principal image for product with ID 12
    ('https://www.groverallman.com.au/cdn/shop/products/unlimited-32.jpg?v=1406092070', 'Main image for Product 12', true, 12),
-- Principal image for product with ID 13
    ('https://plus.unsplash.com/premium_photo-1682391039938-e9782294c1a3?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8ZGolMjBtaXhlcnxlbnwwfHwwfHx8MA%3D%3D', 'Main image for Product 13', true, 13),
-- Principal image for product with ID 14
    ('https://upload.wikimedia.org/wikipedia/commons/0/0c/Shure_mikrofon_55S.jpg', 'Main image for Product 14', true, 14),
-- Principal image for product with ID 15
    ('https://m.media-amazon.com/images/I/71oYw-nKBZL._AC_UF1000,1000_QL80_.jpg', 'Main image for Product 15', true, 15);

-- Insert data into the 'roles' table
INSERT INTO roles (name, description) VALUES ('Administrator', 'Manages everything');
INSERT INTO roles (name, description) VALUES ('Sales Manager', 'Manages product price, customers, shipping, orders and sales report');
INSERT INTO roles (name, description) VALUES ('Editor', 'Manages categories, brands, products, articles, and menus');
INSERT INTO roles (name, description) VALUES ('Shipping Manager', 'Views products, view orders, and update order status');
INSERT INTO roles (name, description) VALUES ('Assistant', 'Manages questions and reviews');

-- Insert data into 'users' table
INSERT INTO users (email, first_name, last_name, password, photo, enabled) VALUES
('alice.smith@example.com', 'Alice', 'Smith', 'aL1c3$S3cP@ss', NULL, TRUE),
('bob.jones@example.com', 'Bob', 'Jones', 'b0b$P@ss123!', NULL, TRUE),
('charlie.brown@example.com', 'Charlie', 'Brown', 'Ch@rl13Br0wnP@ss!', NULL, FALSE),
('diana.prince@example.com', 'Diana', 'Prince', 'D1@n@W0nd3rP@ss', NULL, TRUE),
('eve.adams@example.com', 'Eve', 'Adams', 'Ev3$Ad@msS3c', NULL, FALSE);

-- Assign roles to Alice Smith
INSERT INTO user_roles (user_id, role_id) VALUES
('alice.smith@example.com', 1), -- Administrator
('alice.smith@example.com', 2); -- Sales Manager

-- Assign roles to Bob Jones
INSERT INTO user_roles (user_id, role_id) VALUES
('bob.jones@example.com', 2), -- Sales Manager
('bob.jones@example.com', 3); -- Editor

-- Assign roles to Charlie Brown
INSERT INTO user_roles (user_id, role_id) VALUES
('charlie.brown@example.com', 4); -- Shipping Manager

-- Assign roles to Diana Prince
INSERT INTO user_roles (user_id, role_id) VALUES
('diana.prince@example.com', 1), -- Administrator
('diana.prince@example.com', 5); -- Assistant

-- Assign roles to Eve Adams
INSERT INTO user_roles (user_id, role_id) VALUES
('eve.adams@example.com', 3), -- Editor
('eve.adams@example.com', 5); -- Assistant