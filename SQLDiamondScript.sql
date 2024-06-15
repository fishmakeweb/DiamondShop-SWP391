create database diamondshopdb;
use diamondshopdb;
-- CUSTOMER TABLE
CREATE TABLE IF NOT EXISTS Customer(
    user_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(200),
    registered_date DATE,
    username VARCHAR(50) UNIQUE,
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
    username VARCHAR(50) UNIQUE,
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
    giaid BIGINT UNIQUE,
    price FLOAT,
    img VARCHAR(1000),
    FOREIGN KEY (shape_id) REFERENCES Shape(shape_id),
    FOREIGN KEY (measurement_id) REFERENCES Measurement(measurement_id),
    FOREIGN KEY (carat_id) REFERENCES Carat(carat_id),
    FOREIGN KEY (color_id) REFERENCES Color(color_id),
    FOREIGN KEY (cut_id) REFERENCES Cut(cut_id),
    FOREIGN KEY (clarity_id) REFERENCES Clarity(clarity_id),
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
    size_id BIGINT,
    img VARCHAR(400),
    price FLOAT,
    `date` Date,
    FOREIGN KEY (diamond_id) REFERENCES Diamond(diamond_id),
    FOREIGN KEY (material_id) REFERENCES Material(material_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id),
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
-- INSERT INTO Customer (full_name, email, address, registered_date, username, password) VALUES
-- ('John Doe', 'john.doe@example.com', '123 Elm St, Somewhere', '2024-05-01', 'johnny', 'pass1234'),
-- ('Jane Smith', 'jane.smith@example.com', '456 Oak St, Anywhere', '2024-05-02', 'janes', 'ass5678'),
-- ('Alice Johnson', 'alice.j@example.com', '789 Pine St, Nowhere', '2024-05-03', 'alicej', 'ass91011'),
-- ('Bob Brown', 'bob.brown@example.com', '321 Maple St, Everywhere', '2024-05-04', 'bobbyb', 'ass1213'),
-- ('Charlie Davis', 'charlie.d@example.com', '654 Spruce St, Thisplace', '2024-05-05', 'charlied', 'ass1415'),
-- ('Diana Adams', 'diana.a@example.com', '987 Cedar St, Thatplace', '2024-05-06', 'diana2024', 'ass1617'),
-- ('Evan Foster', 'evan.f@example.com', '159 Birch St, Someplace', '2024-05-07', 'evanf', 'pass1819'),
-- ('Fiona Green', 'fiona.g@example.com', '753 Willow St, Whatplace', '2024-05-08', 'fionag', 'pass2021'),
-- ('George Hill', 'george.h@example.com', '357 Aspen St, Whereplace', '2024-05-09', 'georgeh', 'pass2223'),
-- ('Hannah East', 'hannah.e@example.com', '951 Redwood St, Whoplace', '2024-05-10', 'hannahe', 'pass2425');

INSERT INTO Role (role_name) VALUES ('ROLE_SALESTAFF'),('ROLE_DELIVERYSTAFF'),('ROLE_MANAGER'),('ROLE_ADMIN');

-- INSERT INTO Staff (full_name, email, username, password, role_id) VALUES
-- ('John Doe', 'john.doe@example.com', 'johnsales', '{noop}pass1234', 1),
-- ('Jane Smith', 'jane.smith@example.com', 'janesales', '{noop}pass5678', 1),
-- ('Emily Johnson', 'emily.johnson@example.com', 'emilydelivery', '{noop}pass9012', 2),
-- ('Michael Brown', 'michael.brown@example.com', 'michaeldelivery', '{noop}pass3456', 2),
-- ('Alice Martin', 'alice.martin@example.com', 'alicemanager', '{noop}pass7890', 3),
-- ('John Cena', 'john.cena@example.com', 'johnadmin', '{noop}pass7891', 4);

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

-- INSERT INTO Support (date, message, customer_id, staff_id) VALUES
-- ('2024-05-01', 'Issue with recent purchase, please assist.', 1, 1),
-- ('2024-05-02', 'Question about product warranty.', 1, 2),
-- ('2024-05-03', 'Need help with setting up an account.', 2, 1),
-- ('2024-05-04', 'Payment did not go through, need assistance.', 2, 2);

-- Insert values into Category table
INSERT INTO Category (category_name) VALUES
('Engagement Rings'),
('Fashion Rings'),
('Stud Earrings'),
('Pendants'),
('Diamond Chains');

-- Insert values into Material table
INSERT INTO Material (material_name) VALUES
('Gold'),
('Silver'),
('Platinum'),
('White Gold'),
('Rose Gold');

-- Insert values into Gemstone table
INSERT INTO Gemstone (gemstone_name) VALUES
('Diamond'),
('Ruby'),
('Sapphire'),
('Emerald'),
('Topaz');

-- Insert values into Size table
INSERT INTO Size (type, size_number, unit) VALUES
('Ring', 6, 'US'),
('Ring', 7, 'US'),
('Ring', 8, 'US'),
('Chain Length', 18, 'inches'),
('Chain Length', 20, 'inches'),
('Earring Diameter', 0.5, 'inches'),
('Earring Diameter', 0.75, 'inches'),
('Pendant Height', 1, 'inches'),
('Pendant Height', 1.5, 'inches'),
('Pendant Height', 2, 'inches');

-- Example entries for Jewelry table (ten for each category)
-- Engagement Rings
INSERT INTO Jewelry (name, material_id, category_id, gemstone_id, size_id, img, price)
VALUES
('Classic Solitaire Ring', 1, 1, 1, 1, 'CRS-1.jpg', 1500.00),
('Vintage Diamond Ring', 2, 1, 1, 2, 'VDR-1.jpg', 1600.00),
('Halo Diamond Ring', 3, 1, 1, 3, 'HDR-1.jpg', 1700.00),
('Three-Stone Diamond Ring', 4, 1, 1, 1, 'TDR-1.jpg', 1800.00),
('Pave Diamond Ring', 5, 1, 1, 2, 'PDR-1.jpg', 1900.00),
('Bezel Diamond Ring', 1, 1, 1, 3, 'BDR-1.jpg', 2000.00),
('Cathedral Diamond Ring', 2, 1, 1, 1, 'CDR-1.jpg', 2100.00),
('Tension Diamond Ring', 3, 1, 1, 2, 'TDR-2.jpg', 2200.00),
('Channel-Set Diamond Ring', 4, 1, 1, 3, 'CDR-2.jpg', 2300.00),
('Cluster Diamond Ring', 5, 1, 1, 1, 'CDR-3.jpg', 2400.00);

-- Fashion Rings
INSERT INTO Jewelry (name, material_id, category_id, gemstone_id, size_id, img, price)
VALUES
('Cocktail Ring', 1, 2, 2, 1, 'CT-4.jpg', 2500.00),
('Eternity Band Ring', 2, 2, 3, 2, 'EBR-5.jpg', 2600.00),
('Stackable Ring', 3, 2, 4, 3, 'SR-6.jpg', 2700.00),
('Birthstone Ring', 4, 2, 5, 1, 'BR-6.jpg', 2800.00),
('Statement Ring', 5, 2, 1, 2, 'SR-7.jpg', 2900.00),
('Signet Ring', 1, 2, 2, 3, 'SR-8.jpg', 3000.00),
('Open Ring', 2, 2, 3, 1, 'OP-9.jpg', 3100.00),
('Midi Ring', 3, 2, 4, 2, 'MD-10.jpg', 3200.00),
('Thumb Ring', 4, 2, 5, 3, 'TR-11.jpg', 3300.00),
('Knuckle Ring', 5, 2, 1, 1, 'KR-12.jpg', 3400.00);

-- Stud Earrings
INSERT INTO Jewelry (name, material_id, category_id, gemstone_id, size_id, img, price)
VALUES
('Diamond Studs', 1, 3, 1, 6, 'DS-13.jpg', 3500.00),
('Ruby Studs', 2, 3, 2, 7, 'RS-14.jpg', 3600.00),
('Sapphire Studs', 3, 3, 3, 6, 'SS-15.jpg', 3700.00),
('Emerald Studs', 4, 3, 4, 7, 'ER-16.jpg', 3800.00),
('Topaz Studs', 5, 3, 5, 6, 'TS-17.jpg', 3900.00),
('Halo Diamond Studs', 1, 3, 1, 7, 'HDS-18.jpg', 4000.00),
('Bezel Set Studs', 2, 3, 2, 6, 'BSS-19.jpg', 4100.00),
('Prong Set Studs', 3, 3, 3, 7, 'PSS-20.jpg', 4200.00),
('Cluster Studs', 4, 3, 4, 6, 'CS-21.jpg', 4300.00),
('Vintage Studs', 5, 3, 5, 7, 'VS-22.jpg', 4400.00);

-- Pendants
INSERT INTO Jewelry (name, material_id, category_id, gemstone_id, size_id, img, price)
VALUES
('Diamond Solitaire Pendant', 1, 4, 1, 8, 'DSP-23.jpg', 4500.00),
('Ruby Heart Pendant', 2, 4, 2, 9, 'RHP-24.jpg', 4600.00),
('Sapphire Teardrop Pendant', 3, 4, 3, 10, 'STP-25.jpg', 4700.00),
('Emerald Oval Pendant', 4, 4, 4, 8, 'EOP-26.jpg', 4800.00),
('Topaz Circle Pendant', 5, 4, 5, 9, 'TCP-27.jpg', 4900.00),
('Halo Diamond Pendant', 1, 4, 1, 10, 'HDP-28.jpg', 5000.00),
('Bezel Set Pendant', 2, 4, 2, 8, 'BSP-29.jpg', 5100.00),
('Prong Set Pendant', 3, 4, 3, 9, 'PSP-30.jpg', 5200.00),
('Cluster Pendant', 4, 4, 4, 10, 'CP-31.jpg', 5300.00),
('Vintage Pendant', 5, 4, 5, 8, 'VP-32.jpg', 5400.00);

-- Diamond Chains
INSERT INTO Jewelry (name, material_id, category_id, gemstone_id, size_id, img, price)
VALUES
('Classic Diamond Chain', 1, 5, 1, 4, 'CDC-33.jpg', 5500.00),
('Modern Diamond Chain', 2, 5, 1, 5, 'MDC-34.jpg', 5600.00),
('Vintage Diamond Chain', 3, 5, 1, 4, 'VDC-35.jpg', 5700.00),
('Bezel Set Diamond Chain', 4, 5, 1, 5, 'BSDC-35.jpg', 5800.00),
('Halo Diamond Chain', 5, 5, 1, 4, 'HDC-36.jpg', 5900.00),
('Multi-Stone Diamond Chain', 1, 5, 1, 5, 'MDC-37.jpg', 6000.00),
('Three-Stone Diamond Chain', 2, 5, 1, 4, 'TDC-38.jpg', 6100.00),
('Solitaire Diamond Chain', 3, 5, 1, 5, 'SDC-39.jpg', 6200.00),
('Cluster Diamond Chain', 4, 5, 1, 4, 'CDC-40.jpg', 6300.00),
('Prong Set Diamond Chain', 5, 5, 1, 5, 'PSDC-41.jpg', 6400.00);

-- Inserting Products
INSERT INTO Product (diamond_id, jewelry_id) VALUES
(1, null),
(null, 2),
(null, 3);

-- Inserting Carts
-- INSERT INTO Cart (customer_id) VALUES
-- (1),
-- (2);

-- Assuming the Cart and Product tables have these IDs
-- INSERT INTO Cart_item (cart_id, product_id) VALUES
-- (1, 1),
-- (1, 2),
-- (2, 3);

-- Assuming the Customer table has these IDs
-- INSERT INTO `Order` (user_id, order_date) VALUES
-- (1, '2024-01-15'),
-- (2, '2024-01-16');

-- Assuming the Order and Product tables have these IDs
-- INSERT INTO OrderDetail (product_id, order_id) VALUES
-- (1, 1),
-- (2, 1),
-- (3, 2);

-- Assuming the Staff and Order tables have these IDs
-- INSERT INTO Delivery (staff_id, order_id, delivery_date, status) VALUES
-- (1, 1, '2024-02-20', 'Delivered'),
-- (2, 2, '2024-02-21', 'In Transit');

INSERT INTO Article (title, content) VALUES
('The Future of Jewelry', 'Exploring the trends and innovations shaping the future of the jewelry industry.'),
('Guide to Diamond Care', 'Learn how to maintain the brilliance of your diamonds with these simple care tips.'),
('The History of Gemstones', 'A fascinating look back at the rich history and cultural significance of gemstones worldwide.');
