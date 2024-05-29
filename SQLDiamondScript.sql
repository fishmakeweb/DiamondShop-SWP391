create database diamondshopdb;
use diamondshopdb;
-- CUSTOMER TABLE
CREATE TABLE IF NOT EXISTS Customer(
    user_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(200),
    registered_date DATE,
    username VARCHAR(50),
    password VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS Role(
    role_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL
    );

-- STAFF TABLE
CREATE TABLE IF NOT EXISTS Staff(
    staff_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    email VARCHAR(50),
    username VARCHAR(50),
    password VARCHAR(50),
    role_id bigint,
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
    );

-- SUPPORT TABLE
CREATE TABLE IF NOT EXISTS Support(
    support_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    message VARCHAR(800),
    customer_id BIGINT,
    staff_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(user_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id)
    );

-- DIAMOND ATTRIBUTE TABLES
CREATE TABLE IF NOT EXISTS Cut(
    cut_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    cut_description VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Clarity(
     clarity_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
     clarity_description VARCHAR(10)
    );
CREATE TABLE IF NOT EXISTS Measurement(
    measurement_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    length DECIMAL(5,2),
    width DECIMAL(5,2),
    height DECIMAL(5,2)
    );
CREATE TABLE IF NOT EXISTS Shape(
    shape_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    shape_description VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Symmetry(
    symmetry_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    symmetry_description VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Fluorescence(
    fluorescence_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    fluorescence_description VARCHAR(20)
    );
CREATE TABLE IF NOT EXISTS Color(
    color_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    color_description CHAR(1)
    );
CREATE TABLE IF NOT EXISTS Carat(
    carat_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    carat DECIMAL(3,2)
    );
CREATE TABLE IF NOT EXISTS Polish(
    polish_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    polish_description VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS GIA(
    giaid  BIGINT PRIMARY KEY,
    issue_date DATE
);


-- DIAMOND TABLE
CREATE TABLE IF NOT EXISTS Diamond(
    diamond_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    shape_id BIGINT,
    measurement_id BIGINT,
    carat_id BIGINT,
    color_id BIGINT,
    cut_id BIGINT,
    clarity_id BIGINT,
    polish_id BIGINT,
    symmetry_id BIGINT,
    fluorescence_id BIGINT,
    giaid BIGINT UNIQUE,
    price FLOAT,
    img VARCHAR(1000),
    FOREIGN KEY (shape_id) REFERENCES Shape(shape_id),
    FOREIGN KEY (measurement_id) REFERENCES Measurement(measurement_id),
    FOREIGN KEY (carat_id) REFERENCES Carat(carat_id),
    FOREIGN KEY (color_id) REFERENCES Color(color_id),
    FOREIGN KEY (cut_id) REFERENCES Cut(cut_id),
    FOREIGN KEY (clarity_id) REFERENCES Clarity(clarity_id),
    FOREIGN KEY (polish_id) REFERENCES Polish(polish_id),
    FOREIGN KEY (symmetry_id) REFERENCES Symmetry(symmetry_id),
    FOREIGN KEY (fluorescence_id) REFERENCES Fluorescence(fluorescence_id),
    FOREIGN KEY (giaid) REFERENCES GIA(giaid)
    );

-- JEWELRY ATTRIBUTE TABLE
CREATE TABLE IF NOT EXISTS Category(
    category_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Material(
    material_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    material_name VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Gemstone(
    gemstone_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    gemstone_name VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS Size(
    size_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50),
    size_number FLOAT,
    unit VARCHAR(10)
    );

-- JEWELRY TABLE
CREATE TABLE IF NOT EXISTS Jewelry(
    jewelry_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    diamond_id BIGINT,
    name VARCHAR(50),
    material_id BIGINT,
    category_id BIGINT,
    gemstone_id BIGINT,
    size_id BIGINT,
    img VARCHAR(400),
    price FLOAT,
    FOREIGN KEY (diamond_id) REFERENCES Diamond(diamond_id),
    FOREIGN KEY (material_id) REFERENCES Material(material_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id),
    FOREIGN KEY (gemstone_id) REFERENCES Gemstone(gemstone_id),
    FOREIGN KEY (size_id) REFERENCES Size(size_id)
    );

-- PRODUCT TABLE
CREATE TABLE IF NOT EXISTS Product(
product_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
diamond_id BIGINT,
jewelry_id BIGINT,
FOREIGN KEY (diamond_id) REFERENCES Diamond(diamond_id),
    FOREIGN KEY (jewelry_id) REFERENCES Jewelry(jewelry_id)
    );

-- CART TABLE
CREATE TABLE IF NOT EXISTS Cart(
cart_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
customer_id BIGINT,
FOREIGN KEY (customer_id) REFERENCES Customer(user_id)
    );

-- CART-ITEM TABLE
CREATE TABLE IF NOT EXISTS Cart_item(
cart_id BIGINT,
product_id BIGINT,
PRIMARY KEY (cart_id, product_id),
FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

-- ORDER TABLE
CREATE TABLE IF NOT EXISTS `Order`(
    order_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    order_date DATE,
    FOREIGN KEY (user_id) REFERENCES Customer(user_id)
    );

-- ORDER DETAIL TABLE
CREATE TABLE IF NOT EXISTS OrderDetail(
    product_id BIGINT,
    order_id BIGINT,
    PRIMARY KEY (product_id, order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id)
    );

-- DELIVERY TABLE
CREATE TABLE IF NOT EXISTS Delivery(
    delivery_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    staff_id BIGINT,
    order_id BIGINT,
    delivery_date DATE,
    status VARCHAR(10),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id),
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id)
    );

-- ARTICLE TABLE
CREATE TABLE IF NOT EXISTS Article(
    article_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200),
    content VARCHAR(1000)
    );


-- insert customer
INSERT INTO Customer (full_name, email, address, registered_date, username, password) VALUES
('John Doe', 'john.doe@example.com', '123 Elm St, Somewhere', '2024-05-01', 'johnny', '{noop}pass1234'),
('Jane Smith', 'jane.smith@example.com', '456 Oak St, Anywhere', '2024-05-02', 'janes', '{noop}pass5678'),
('Alice Johnson', 'alice.j@example.com', '789 Pine St, Nowhere', '2024-05-03', 'alicej', '{noop}pass91011'),
('Bob Brown', 'bob.brown@example.com', '321 Maple St, Everywhere', '2024-05-04', 'bobbyb', '{noop}pass1213'),
('Charlie Davis', 'charlie.d@example.com', '654 Spruce St, Thisplace', '2024-05-05', 'charlied', '{noop}pass1415'),
('Diana Adams', 'diana.a@example.com', '987 Cedar St, Thatplace', '2024-05-06', 'diana2024', '{noop}pass1617'),
('Evan Foster', 'evan.f@example.com', '159 Birch St, Someplace', '2024-05-07', 'evanf', '{noop}pass1819'),
('Fiona Green', 'fiona.g@example.com', '753 Willow St, Whatplace', '2024-05-08', 'fionag', '{noop}pass2021'),
('George Hill', 'george.h@example.com', '357 Aspen St, Whereplace', '2024-05-09', 'georgeh', '{noop}pass2223'),
('Hannah East', 'hannah.e@example.com', '951 Redwood St, Whoplace', '2024-05-10', 'hannahe', '{noop}pass2425');

INSERT INTO Role (role_name) VALUES ('ROLE_SALESTAFF'),('ROLE_DELIVERYSTAFF'),('ROLE_MANAGER'),('ROLE_ADMIN');

INSERT INTO Staff (full_name, email, username, password, role_id) VALUES
('John Doe', 'john.doe@example.com', 'johnsales', '{noop}pass1234', 1),
('Jane Smith', 'jane.smith@example.com', 'janesales', '{noop}pass5678', 1),
('Emily Johnson', 'emily.johnson@example.com', 'emilydelivery', '{noop}pass9012', 2),
('Michael Brown', 'michael.brown@example.com', 'michaeldelivery', '{noop}pass3456', 2),
('Alice Martin', 'alice.martin@example.com', 'alicemanager', '{noop}pass7890', 3),
('John Cena', 'john.cena@example.com', 'johnadmin', '{noop}pass7891', 4);

INSERT INTO Cut (cut_description) VALUES
('Round'),
('Princess'),
('Oval'),
('Marquise'),
('Pear');

INSERT INTO Clarity (clarity_description) VALUES
('FL'),
('IF'),
('VVS1'),
('VVS2'),
('VS1');

INSERT INTO Measurement (length, width, height) VALUES
(5.10, 5.10, 3.20),
(4.50, 4.50, 2.80),
(6.00, 6.00, 3.70),
(7.00, 5.00, 4.00),
(8.00, 5.50, 4.50);

INSERT INTO Shape (shape_description) VALUES
('Round'),
('Princess'),
('Oval'),
('Marquise'),
('Pear');

INSERT INTO Symmetry (symmetry_description) VALUES
('Excellent'),
('Very Good'),
('Good'),
('Fair'),
('Poor');

INSERT INTO Fluorescence (fluorescence_description) VALUES
('None'),
('Faint'),
('Medium'),
('Strong'),
('Very Strong');

INSERT INTO Color (color_description) VALUES
('D'),
('E'),
('F'),
('G'),
('H');

INSERT INTO Carat (carat) VALUES
(0.30),
(0.50),
(1.00),
(1.50),
(2.00);

INSERT INTO Polish (polish_description) VALUES
('Excellent'),
('Very Good'),
('Good'),
('Fair'),
('Poor');

INSERT INTO GIA (giaid,issue_date) VALUES
(1,'2024-01-01'),
(2,'2024-02-01'),
(3,'2024-03-01'),
(4,'2024-04-01'),
(5,'2024-05-01');

INSERT INTO Diamond (shape_id, measurement_id, carat_id, color_id, cut_id, clarity_id, polish_id, symmetry_id, fluorescence_id, giaid, price, img) VALUES
(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7000.00, 'image2.jpg'),
(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 15000.00, 'image3.jpg'),
(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 25000.00, 'image4.jpg'),
(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 35000.00, 'image5.jpg');

INSERT INTO Support (date, message, customer_id, staff_id) VALUES
('2024-05-01', 'Issue with recent purchase, please assist.', 1, 1),
('2024-05-02', 'Question about product warranty.', 1, 2),
('2024-05-03', 'Need help with setting up an account.', 2, 1),
('2024-05-04', 'Payment did not go through, need assistance.', 2, 2);

INSERT INTO Category (category_name) VALUES
('Engagement Rings'),
('Wedding Bands'),
('Fashion Rings'),
('Stud Earrings'),
('Hoop Earrings'),
('Pendants'),
('Diamond Chains'),
('Statement Necklaces'),
('Tennis Bracelets'),
('Bangle Bracelets'),
('Charm Bracelets'),
('Solitaire Pendants'),
('Cluster Pendants'),
('Personalized Pendants'),
('Vintage Brooches'),
('Modern Brooches'),
('Chain Anklets'),
('Charm Anklets'),
('Classic Cufflinks'),
('Themed Cufflinks');

INSERT INTO Material (material_name) VALUES
('Gold'),
('Platinum'),
('Silver'),
('Rose Gold'),
('White Gold'),
('Titanium'),
('Stainless Steel'),
('Bronze'),
('Copper'),
('Aluminum'),
('Nickel'),
('Pewter'),
('Brass'),
('18k Gold'),
('24k Gold'),
('Palladium'),
('Ceramic'),
('Leather'),
('Fabric'),
('Rubber'),
('Rhodium'),
('Bamboo'),
('Silk'),
('Carbon Fiber'),
('Velvet'),
('Acrylic'),
('Suede');

INSERT INTO Gemstone (gemstone_name) VALUES
('Diamond'),
('Ruby'),
('Sapphire'),
('Emerald'),
('Topaz'),
('Garnet'),
('Amethyst'),
('Aquamarine'),
('Pearl'),
('Opal'),
('Citrine'),
('Turquoise'),
('Moonstone'),
('Peridot'),
('Quartz'),
('Tanzanite'),
('Tourmaline'),
('Zircon'),
('Morganite'),
('Lapis Lazuli');


INSERT INTO Size (type, size_number, unit) VALUES
('Ring Size', 6, 'US'),
('Ring Size', 7, 'US'),
('Ring Size', 8, 'US'),
('Bracelet Length', 18, 'cm'),
('Bracelet Length', 20, 'cm'),
('Bracelet Length', 22, 'cm'),
('Necklace Length', 40, 'cm'),
('Necklace Length', 45, 'cm'),
('Necklace Length', 50, 'cm'),
('Earring Length', 2, 'cm'),
('Earring Length', 3, 'cm'),
('Earring Length', 4, 'cm'),
('Pendant Size', 1.5, 'cm'),
('Pendant Size', 2, 'cm'),
('Pendant Size', 2.5, 'cm'),
('Anklet Size', 22, 'cm'),
('Anklet Size', 24, 'cm'),
('Anklet Size', 26, 'cm'),
('Cufflink Size', 1.5, 'cm'),
('Cufflink Size', 2, 'cm'),
('Earring Length', 5, 'cm'),
('Earring Length', 6, 'cm'),
('Necklace Length', 55, 'cm'),
('Necklace Length', 60, 'cm'),
('Bracelet Length', 16, 'cm'),
('Bracelet Length', 24, 'cm'),
('Ring Size', 9, 'US');

-- Engagement Rings
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(1, 'Classic Engagement Ring', 1, 1, 1, 1, 'ER-1.jpg', 500.00),
(2, 'Modern Engagement Ring', 2, 1, 2, 2, 'ER-2.webp', 800.00),
(NULL, 'Vintage Engagement Ring', 3, 1, 3, 3, 'ER-3.jpg', 750.00);

-- Wedding Bands
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(3, 'Gold Wedding Band', 4, 2, NULL, 4, 'WB-1.jpg', 300.00),
(4, 'Platinum Wedding Band', 5, 2, NULL, 5, 'WB-2.jpg', 900.00),
(NULL, 'Silver Wedding Band', 6, 2, NULL, 6, 'WB-3.webp', 200.00);

-- Fashion Rings
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Rose Gold Fashion Ring', 7, 3, 4, 7, 'FR-1.webp', 250.00),
(NULL, 'Stackable Fashion Ring', 8, 3, 5, 8, 'FR-2.jpg', 150.00),
(NULL, 'Statement Fashion Ring', 9, 3, 6, 9, 'FR-3.jpg', 180.00);

-- Stud Earrings
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Simple Stud Earrings', 10, 4, 7, 10, 'SE-1.webp', 100.00),
(NULL, 'Gemstone Stud Earrings', 11, 4, 8, 11, 'SE-2.jpg', 200.00),
(NULL, 'Diamond Stud Earrings', 1, 4, 9, 12, 'SE-3.webp', 1200.00);

-- Hoop Earrings
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Gold Hoop Earrings', 12, 5, NULL, 13, 'HE-1.webp', 350.00),
(NULL, 'Silver Hoop Earrings', 13, 5, NULL, 14, 'HE-2.webp', 300.00),
(NULL, 'Large Hoop Earrings', 14, 5, NULL, 15, 'HE-3.jpg', 400.00);

-- Pendants
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Heart Shaped Pendant', 15, 6, 10, 16, 'PD-1.jpg', 250.00),
(NULL, 'Pearl Pendant', 16, 6, 11, 17, 'PD-2.jpg', 220.00),
(NULL, 'Gold Disc Pendant', 17, 6, NULL, 18, 'PD-3.webp', 180.00);

-- Diamond Chains
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Elegant Diamond Chain', 18, 7, 12, 19, 'DC-1.webp', 2000.00),
(NULL, 'Simple Diamond Chain', 19, 7, 13, 20, 'DC-2.webp', 1500.00),
(NULL, 'Layered Diamond Chain', 20, 7, 14, 17, 'DC-3.jpg', 2500.00);

-- Statement Necklaces
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Bold Statement Necklace', 21, 8, 15, 19, 'SN-1.webp', 500.00),
(NULL, 'Colorful Statement Necklace', 22, 8, 16, 23, 'SN-2.webp', 600.00),
(NULL, 'Geometric Statement Necklace', 23, 8, 17, 24, 'SN-3.jpg', 550.00);

-- Tennis Bracelets
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(3, 'Classic Tennis Bracelet', 24, 9, 18, 18, 'TB-1.webp', 1200.00),
(4, 'Modern Tennis Bracelet', 25, 9, 19, 16, 'TB-2.jpg', 1300.00),
(NULL, 'Vintage Tennis Bracelet', 26, 9, 20, 19, 'TB-3.jpg', 1100.00);

-- -------------------------------------------------------------------------------
-- Bangle Bracelets
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Gold Bangle', 14, 10, NULL, 4, 'BB-1.webp', 220.00),
(NULL, 'Silver Bangle', 13, 10, NULL, 5, 'BB-2.jpg', 180.00),
(NULL, 'Decorative Bangle', 17, 10, 8, 6, 'BB-3.webp', 300.00);

-- Charm Bracelets
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Heart Charm Bracelet', 12, 11, 9, 26, 'CB-1.jpg', 150.00),
(NULL, 'Beaded Charm Bracelet', 15, 11, 10, 26, 'CB-2.webp', 130.00),
(NULL, 'Gemstone Charm Bracelet', 11, 11, 11, 26, 'CB-3.webp', 175.00);

-- Solitaire Pendants
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(1, 'Diamond Solitaire Pendant', 14, 12, 1, 14, 'SP-1.jpg', 1000.00),
(NULL, 'Sapphire Solitaire Pendant', 19, 12, 3, 14, 'SP-2.jpg', 900.00),
(NULL, 'Ruby Solitaire Pendant', 20, 12, 2, 14, 'SP-3.webp', 850.00);

-- Cluster Pendants
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Multi-Gem Cluster Pendant', 21, 13, 15, 15, 'CB-1.jpg', 500.00),
(NULL, 'Diamond Cluster Pendant', 18, 13, 1, 15, 'CB-2.webp', 1200.00),
(NULL, 'Pearl Cluster Pendant', 22, 13, 9, 15, 'CB-3.webp', 300.00);

-- Personalized Pendants
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Initials Pendant', 24, 14, NULL, 16, 'PP-1.webp', 250.00),
(NULL, 'Birthstone Pendant', 25, 14, 19, 16, 'PP-2.avif', 200.00),
(NULL, 'Engraved Pendant', 26, 14, NULL, 16, 'PP-3.avif', 300.00);

-- Vintage Brooches
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Art Deco Brooch', 12, 15, 17, 20, 'VP-1.jpg', 450.00),
(NULL, 'Victorian Brooch', 15, 15, 16, 20, 'VP-2.jpg', 550.00),
(NULL, 'Edwardian Brooch', 17, 15, 18, 20, 'VP-3.jpg', 500.00);

-- Modern Brooches
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Contemporary Silver Brooch', 13, 16, NULL, 20, 'MB-1.avif', 300.00),
(NULL, 'Geometric Gold Brooch', 14, 16, NULL, 20, 'MB-2.jpg', 350.00),
(NULL, 'Minimalist Brooch', 11, 16, NULL, 20, 'MB-3.webp', 250.00);

-- Chain Anklets
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Simple Chain Anklet', 14, 17, NULL, 17, 'CHAIN-A-1.jpg', 120.00),
(NULL, 'Layered Chain Anklet', 12, 17, NULL, 17, 'CHAIN-A-2.webp', 150.00),
(NULL, 'Gold Chain Anklet', 18, 17, NULL, 17, 'CHAIN-A-3.avif', 140.00);

-- Charm Anklets
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Beaded Charm Anklet', 19, 18, 20, 18, 'CHARM-A-1.webp', 130.00),
(NULL, 'Silver Charm Anklet', 20, 18, NULL, 18, 'CHARM-A-2.jpg', 110.00),
(NULL, 'Gold Charm Anklet', 21, 18, NULL, 18, 'CHARM-A-3.webp', 160.00);

-- Classic Cufflinks
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Silver Oval Cufflinks', 13, 19, NULL, 20, 'CC-1.jpg', 200.00),
(NULL, 'Gold Square Cufflinks', 14, 19, NULL, 20, 'CC-2.webp', 220.00),
(NULL, 'Platinum Classic Cufflinks', 25, 19, NULL, 20, 'CC-3.jpg', 300.00);

-- Themed Cufflinks
INSERT INTO Jewelry (diamond_id, name, material_id, category_id, gemstone_id, size_id, img, price) VALUES
(NULL, 'Nautical Themed Cufflinks', 14, 20, NULL, 20, 'TC-1.webp', 250.00),
(NULL, 'Automotive Themed Cufflinks', 17, 20, NULL, 20, 'TC-2.webp', 270.00),
(NULL, 'Sports Themed Cufflinks', 11, 20, NULL, 20, 'TC-3.webp', 230.00);

-- Inserting Products
INSERT INTO Product (diamond_id, jewelry_id) VALUES
(1, null),
(null, 2),
(null, 3);

-- Inserting Carts
INSERT INTO Cart (customer_id) VALUES
(1),
(2);

-- Assuming the Cart and Product tables have these IDs
INSERT INTO Cart_item (cart_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 3);

-- Assuming the Customer table has these IDs
INSERT INTO `Order` (user_id, order_date) VALUES
(1, '2024-01-15'),
(2, '2024-01-16');

-- Assuming the Order and Product tables have these IDs
INSERT INTO OrderDetail (product_id, order_id) VALUES
(1, 1),
(2, 1),
(3, 2);

-- Assuming the Staff and Order tables have these IDs
INSERT INTO Delivery (staff_id, order_id, delivery_date, status) VALUES
(1, 1, '2024-02-20', 'Delivered'),
(2, 2, '2024-02-21', 'In Transit');

INSERT INTO Article (title, content) VALUES
('The Future of Jewelry', 'Exploring the trends and innovations shaping the future of the jewelry industry.'),
('Guide to Diamond Care', 'Learn how to maintain the brilliance of your diamonds with these simple care tips.'),
('The History of Gemstones', 'A fascinating look back at the rich history and cultural significance of gemstones worldwide.');
