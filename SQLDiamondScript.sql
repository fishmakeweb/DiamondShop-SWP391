CREATE DATABASE diamondshopdb;
USE diamondshopdb;

-- CUSTOMER TABLE
CREATE TABLE IF NOT EXISTS customer(
    user_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(200),
    registered_date DATE,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS role(
    role_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL
    );

-- STAFF TABLE
CREATE TABLE IF NOT EXISTS staff(
    staff_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50),
    email VARCHAR(50),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    role_id bigint,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
    );

-- SUPPORT TABLE
CREATE TABLE IF NOT EXISTS support(
    support_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    message VARCHAR(800),
    customer_id BIGINT,
    staff_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(user_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
    );

-- DIAMOND ATTRIBUTE TABLES
CREATE TABLE IF NOT EXISTS cut(
    cut_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    cut_description VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS clarity(
     clarity_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
     clarity_description VARCHAR(10)
    );
CREATE TABLE IF NOT EXISTS measurement(
    measurement_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    length DECIMAL(5,2),
    width DECIMAL(5,2),
    height DECIMAL(5,2)
    );
CREATE TABLE IF NOT EXISTS shape(
    shape_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    shape_description VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS color(
    color_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    color_description CHAR(1)
    );
CREATE TABLE IF NOT EXISTS carat(
    carat_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    carat DECIMAL(3,2)
    );
    


CREATE TABLE IF NOT EXISTS gia(
    gia_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    issue_date DATE,
    gia_number VARCHAR(50) UNIQUE
);

-- DIAMOND TABLE
CREATE TABLE IF NOT EXISTS diamond(
    diamond_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    shape_id BIGINT,
    measurement_id BIGINT,
    carat_id BIGINT,
    color_id BIGINT,
    cut_id BIGINT,
    clarity_id BIGINT,
    gia_id BIGINT UNIQUE,
    price FLOAT,
    img VARCHAR(1000),
    FOREIGN KEY (shape_id) REFERENCES shape(shape_id),
    FOREIGN KEY (measurement_id) REFERENCES measurement(measurement_id),
    FOREIGN KEY (carat_id) REFERENCES carat(carat_id),
    FOREIGN KEY (color_id) REFERENCES color(color_id),
    FOREIGN KEY (cut_id) REFERENCES cut(cut_id),
    FOREIGN KEY (clarity_id) REFERENCES clarity(clarity_id),
    FOREIGN KEY (gia_id) REFERENCES gia(gia_id)
    );

-- JEWELRY ATTRIBUTE TABLE
CREATE TABLE IF NOT EXISTS category(
    category_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50)
    );
CREATE TABLE IF NOT EXISTS material(
    material_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    material_name VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS size(
    size_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50),
    size_number FLOAT,
    unit VARCHAR(10)
    );

-- JEWELRY TABLE
CREATE TABLE IF NOT EXISTS jewelry(
    jewelry_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    diamond_id BIGINT,
    name VARCHAR(50),
    material_id BIGINT,
    category_id BIGINT,
    size_id BIGINT,
    img VARCHAR(400),
    price FLOAT,
    quantity INT,
    `date` Date,
    FOREIGN KEY (diamond_id) REFERENCES diamond(diamond_id),
    FOREIGN KEY (material_id) REFERENCES material(material_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    FOREIGN KEY (size_id) REFERENCES size(size_id)
    );

-- PRODUCT TABLE
CREATE TABLE IF NOT EXISTS product(
    product_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    diamond_id BIGINT,
    jewelry_id BIGINT,
    FOREIGN KEY (diamond_id) REFERENCES diamond(diamond_id),
    FOREIGN KEY (jewelry_id) REFERENCES jewelry(jewelry_id)
    );


-- ORDER TABLE
CREATE TABLE IF NOT EXISTS `order`(
    order_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    order_date DATE,
    FOREIGN KEY (user_id) REFERENCES customer(user_id)
    );
CREATE TABLE IF NOT EXISTS orderprocess(
	orderprocess_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    orderprocess_description VARCHAR(200),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id)
);
-- ORDER DETAIL TABLE
CREATE TABLE IF NOT EXISTS orderdetail(
    product_id BIGINT,
    order_id BIGINT,
    PRIMARY KEY (product_id, order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id)
    );



-- ARTICLE TABLE
CREATE TABLE IF NOT EXISTS article(
    article_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200),
    content VARCHAR(1000)
    );

-- Insert statements remain the same, with the table names adjusted to lowercase

INSERT INTO role (role_name) VALUES ('ROLE_SALESTAFF'),('ROLE_DELIVERYSTAFF'),('ROLE_MANAGER'),('ROLE_ADMIN');

INSERT INTO cut (cut_description) VALUES
('Round'),
('Princess'),
('Oval'),
('Marquise'),
('Pear');

INSERT INTO clarity (clarity_description) VALUES
('FL'),
('IF'),
('VVS1'),
('VVS2'),
('VS1');

INSERT INTO measurement (length, width, height) VALUES
(5.10, 5.10, 3.20),
(4.50, 4.50, 2.80),
(6.00, 6.00, 3.70),
(7.00, 5.00, 4.00),
(8.00, 5.50, 4.50);

INSERT INTO shape (shape_description) VALUES
('Round'),
('Princess'),
('Oval'),
('Marquise'),
('Pear');



INSERT INTO color (color_description) VALUES
('D'),
('E'),
('F'),
('G'),
('H');

INSERT INTO carat (carat) VALUES
(0.30),
(0.50),
(1.00),
(1.50),
(2.00);


-- Example entries for Jewelry table (ten for each category)
-- Engagement Rings


-- Insert values into Category table
INSERT INTO category (category_name) VALUES
('Engagement Rings'),
('Fashion Rings'),
('Stud Earrings'),
('Pendants'),
('Diamond Chains');

-- Insert values into Material table
INSERT INTO material (material_name) VALUES
('Gold'),
('Silver'),
('Platinum'),
('White Gold'),
('Rose Gold');

-- Insert values into Size table
INSERT INTO size (type, size_number, unit) VALUES
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

-- Inserting Articles
INSERT INTO article (title, content) VALUES
('The Future of Jewelry', 'Exploring the trends and innovations shaping the future of the jewelry industry.'),
('Guide to Diamond Care', 'Learn how to maintain the brilliance of your diamonds with these simple care tips.'),
('The History of Gemstones', 'A fascinating look back at the rich history and cultural significance of gemstones worldwide.');
