	CREATE DATABASE diamondshopdb;
	USE diamondshopdb;
    
	INSERT INTO role (role_name) VALUES ('ROLE_SALESTAFF'),('ROLE_ADMIN');

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

-- Insert shape descriptions
INSERT INTO shape (shape_description, shape_img) VALUES
('Hình tròn', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720164069333_WB-3.jpg"),
('Hình bầu dục', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720164128616_TDR-2.jpg"),
('Hình vuông', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720164149029_SR-8.jpg"),
('Hình chữ nhật', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720164172420_MDC-37.jpg");

-- Insert color descriptions
INSERT INTO color (color_description) VALUES
('D'), ('E'), ('F'), ('G'), ('H');

-- Insert carat weights
INSERT INTO carat (carat) VALUES
(0.30), (0.50), (1.00), (1.50), (2.00);

-- Insert category descriptions
INSERT INTO category (category_name, category_img) VALUES
('Nhẫn Đính Hôn', 'https://ap-south-1.linodeobjects.com/diamondshop-img/1720163219982_eng-ring-sample.png'),
('Dây Chuyền', 'https://ap-south-1.linodeobjects.com/diamondshop-img/1720163844603_PD-1.jpg'),
('Nhẫn Thời Trang', 'https://ap-south-1.linodeobjects.com/diamondshop-img/1720163757950_CT-4.jpg');

-- Insert material descriptions
INSERT INTO material (material_name, material_img) VALUES
('Vàng', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720163901663_FR-2.jpg"),
('Bạc', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720163929268_BR-6.jpg"),
('Bạch Kim', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720163950896_ER-3.jpg"),
('Vàng Trắng', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720163975894_PDR-1.jpg"),
('Vàng Hồng', "https://ap-south-1.linodeobjects.com/diamondshop-img/1720164003532_RS-14.jpg");

-- Insert size descriptions
INSERT INTO size (type, size_number, unit) VALUES
('Nhẫn', 6, 'US'), ('Nhẫn', 7, 'US'), ('Nhẫn', 8, 'US'),
('Dây Chuyền', 16, 'inches'), ('Dây Chuyền', 18, 'inches'), ('Dây Chuyền', 20, 'inches'), ('Dây Chuyền', 22, 'inches');

-- Insert order status descriptions
INSERT INTO order_status (status_description) VALUES
('Trong Giỏ Hàng'),
('Chờ Xác Nhận'),
('Đang Xử Lý'),
('Hoàn Thành');
