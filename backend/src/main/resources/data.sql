-- Insert data into the categories table for music-related products
INSERT INTO categories (name, description) VALUES
('Music Albums', 'Physical or digital albums containing a collection of songs'),
('Instruments', 'Musical instruments for playing and composing music'),
('Vinyl Records', 'Vinyl discs for playing music, commonly used for vintage or collector items'),
('Music Accessories', 'Accessories for music players, such as headphones, speakers, etc.'),
('Music Equipment', 'Sound systems and equipment for music production and performances');=

-- Insert products into the 'products' table for the 'Music Albums' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at) VALUES
('Greatest Hits', 'Compilation album', 'A collection of the best songs from various albums by the artist.', 1, 15.99, 10, true, 100, 10.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Live Concert Performance', 'Live album recording', 'A live performance of popular songs from the artist recorded on stage.', 1, 19.99, 15, true, 50, 12.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Acoustic Sessions', 'Unplugged album', 'An acoustic version of famous tracks performed in a studio setting.', 1, 12.99, 20, true, 75, 8.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert products into the 'products' table for the 'Instruments' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at) VALUES
('Electric Guitar', 'String instrument', 'A solid body electric guitar perfect for rock music.', 2, 299.99, 5, true, 30, 150.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Acoustic Guitar', 'String instrument', 'A classic acoustic guitar for beginners and professionals alike.', 2, 199.99, 10, true, 50, 120.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Drum Set', 'Percussion instrument', 'A full drum set with cymbals, perfect for creating rhythm.', 2, 499.99, 5, true, 20, 350.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert products into the 'products' table for the 'Vinyl Records' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at) VALUES
('Classic Rock Vinyl', 'Vinyl record', 'A vinyl record featuring classic rock songs.', 3, 25.99, 5, true, 40, 15.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jazz Collection Vinyl', 'Vinyl record', 'A vinyl collection of the best jazz music performances.', 3, 29.99, 10, true, 35, 18.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Vinyl Collection: 80s Hits', 'Vinyl record', 'A collection of 80s hits on vinyl.', 3, 22.99, 8, true, 60, 13.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert products into the 'products' table for the 'Music Accessories' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at) VALUES
('Noise Cancelling Headphones', 'Headphones', 'High-quality headphones with active noise cancellation for an immersive listening experience.', 4, 89.99, 15, true, 100, 50.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Portable Bluetooth Speaker', 'Speaker', 'A small portable speaker with Bluetooth connectivity for music on the go.', 4, 49.99, 10, true, 150, 30.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Guitar Picks (Set of 10)', 'Accessory', 'A set of 10 durable guitar picks for different playing styles.', 4, 9.99, 0, true, 200, 2.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert products into the 'products' table for the 'Music Equipment' category
INSERT INTO products (name, short_description, full_description, category_id, price, discount, is_active, stock_amount, cost, length, width, height, weight, created_at, updated_at) VALUES
('DJ Mixer', 'Music Equipment', 'A professional DJ mixer for mixing tracks at live events.', 5, 299.99, 5, true, 25, 180.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Microphone', 'Music Equipment', 'A high-quality microphone for studio and live performances.', 5, 79.99, 10, true, 100, 40.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sound Amplifier', 'Music Equipment', 'A powerful sound amplifier for high-volume performances and studio work.', 5, 199.99, 8, true, 40, 120.00, 1,1,1,1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert data into the 'images' table

INSERT INTO images (url, description, is_principal, product_id)
VALUES
-- Principal image for product with ID 1
    ('https://example.com/product1_main.jpg', 'Main image for Product 1', true, 1),
    ('https://example.com/product1_image1.jpg', 'Optional image 1 for Product 1', false, 1),
    ('https://example.com/product1_image2.jpg', 'Optional image 2 for Product 1', false, 1),
-- Principal image for product with ID 2
    ('https://example.com/product2_main.jpg', 'Main image for Product 2', true, 2),
    ('https://example.com/product2_image1.jpg', 'Optional image for Product 2', false, 2),
-- Principal image for product with ID 3
    ('https://example.com/product3_main.jpg', 'Main image for Product 3', true, 3),
    ('https://example.com/product3_image1.jpg', 'Optional image 1 for Product 3', false, 3),
    ('https://example.com/product3_image2.jpg', 'Optional image 2 for Product 3', false, 3),
-- Principal image for product with ID 4
    ('https://example.com/product4_main.jpg', 'Main image for Product 4', true, 4),
    ('https://example.com/product4_image1.jpg', 'Optional image for Product 4', false, 4),
-- Principal image for product with ID 5
    ('https://example.com/product5_main.jpg', 'Main image for Product 5', true, 5),
-- Principal image for product with ID 6
    ('https://example.com/product6_main.jpg', 'Main image for Product 6', true, 6),
-- Principal image for product with ID 7
    ('https://example.com/product7_main.jpg', 'Main image for Product 7', true, 7),
-- Principal image for product with ID 8
    ('https://example.com/product8_main.jpg', 'Main image for Product 8', true, 8),
-- Principal image for product with ID 9
    ('https://example.com/product9_main.jpg', 'Main image for Product 9', true, 9),
-- Principal image for product with ID 10
    ('https://example.com/product10_main.jpg', 'Main image for Product 10', true, 10),
-- Principal image for product with ID 11
    ('https://example.com/product11_main.jpg', 'Main image for Product 11', true, 11),
-- Principal image for product with ID 12
    ('https://example.com/product12_main.jpg', 'Main image for Product 12', true, 12),
-- Principal image for product with ID 13
    ('https://example.com/product13_main.jpg', 'Main image for Product 13', true, 13),
-- Principal image for product with ID 14
    ('https://example.com/product14_main.jpg', 'Main image for Product 14', true, 14),
-- Principal image for product with ID 15
    ('https://example.com/product15_main.jpg', 'Main image for Product 15', true, 15);
