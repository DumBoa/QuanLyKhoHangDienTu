create database QLKHDT1;
use QLKHDT1;
CREATE TABLE `nhacungcap` (
  `MA_NCC` varchar(20) NOT NULL,
  `TEN_NHACUNGCAP` varchar(40) NOT NULL,
  `SODIENTHOAI` varchar(15) DEFAULT NULL,
  `DIACHI` varchar(150) NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MA_NCC`),
  UNIQUE KEY `MA_NCC` (`MA_NCC`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `nhap_hang` (
  `IDNH` int NOT NULL AUTO_INCREMENT,
  `MA_PHIEU_NHAP` varchar(20) NOT NULL,
  `MA_NCC` varchar(20) NOT NULL,
  `NGUOI_TAO` varchar(50) NOT NULL,
  `SOLUONG` int DEFAULT NULL,
  `DON_GIA` decimal(15,2) DEFAULT NULL,
  `THOI_GIAN_TAO` datetime NOT NULL,
  `MA_SANPHAM` varchar(20) NOT NULL,
  `TEN_SANPHAM` varchar(40) NOT NULL,
  `TONG_TIEN` decimal(15,2) NOT NULL,
  PRIMARY KEY (`IDNH`),
  KEY `MA_SANPHAM` (`MA_SANPHAM`),
  KEY `idx_ma_ncc` (`MA_NCC`),
  KEY `idx_soluong` (`SOLUONG`),
  KEY `idx_ten_sanpham` (`TEN_SANPHAM`),
  KEY `idx_nguoitao` (`NGUOI_TAO`),
  KEY `idx_thoigian_tao` (`THOI_GIAN_TAO`),
  KEY `idx_ma_phieu_nhap` (`MA_PHIEU_NHAP`),
  CONSTRAINT `nhap_hang_ibfk_1` FOREIGN KEY (`MA_SANPHAM`) REFERENCES `sanpham` (`MA_SANPHAM`) ON DELETE CASCADE,
  CONSTRAINT `nhap_hang_ibfk_2` FOREIGN KEY (`MA_NCC`) REFERENCES `nhacungcap` (`MA_NCC`) ON DELETE CASCADE,
  CONSTRAINT `nhap_hang_chk_1` CHECK ((`SOLUONG` >= 0)),
  CONSTRAINT `nhap_hang_chk_2` CHECK ((`DON_GIA` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sanpham` (
  `MA_SANPHAM` varchar(20) NOT NULL,
  `TEN_SANPHAM` varchar(40) NOT NULL,
  `DON_GIA` decimal(15,2) DEFAULT NULL,
  `XUAT_XU` varchar(50) DEFAULT NULL,
  `LOAI_SANPHAM` varchar(50) DEFAULT NULL,
  `SOLUONG` int DEFAULT '0',
  PRIMARY KEY (`MA_SANPHAM`),
  KEY `idx_ten_sanpham` (`TEN_SANPHAM`),
  KEY `idx_don_gia` (`DON_GIA`),
  KEY `idx_soluong` (`SOLUONG`),
  KEY `idx_ma_sanpham` (`MA_SANPHAM`),
  CONSTRAINT `sanpham_chk_1` CHECK ((`DON_GIA` >= 0)),
  CONSTRAINT `sanpham_chk_2` CHECK ((`SOLUONG` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taikhoan` (
  `TENDANGNHAP` varchar(30) NOT NULL,
  `MATKHAU` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL,
  `hovaten` varchar(45) NOT NULL,
  PRIMARY KEY (`TENDANGNHAP`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `thongke` (
  `STT` int NOT NULL AUTO_INCREMENT,
  `MA_SANPHAM` varchar(20) NOT NULL,
  `TEN_SANPHAM` varchar(40) NOT NULL,
  `TONG_SOLUONG_NHAP` int DEFAULT '0',
  `TONG_SOLUONG_XUAT` int DEFAULT '0',
  PRIMARY KEY (`STT`),
  KEY `MA_SANPHAM` (`MA_SANPHAM`),
  CONSTRAINT `thongke_ibfk_1` FOREIGN KEY (`MA_SANPHAM`) REFERENCES `sanpham` (`MA_SANPHAM`) ON DELETE CASCADE,
  CONSTRAINT `thongke_chk_1` CHECK ((`TONG_SOLUONG_NHAP` >= 0)),
  CONSTRAINT `thongke_chk_2` CHECK ((`TONG_SOLUONG_XUAT` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tonkho` (
  `MA_HANG` varchar(20) NOT NULL,
  `TEN_HANG` varchar(40) NOT NULL,
  `SOLUONG` int DEFAULT NULL,
  `DON_GIA` decimal(15,2) DEFAULT NULL,
  `LOAI_DO_DIEN_TU` varchar(50) DEFAULT NULL,
  `MA_SANPHAM` varchar(20) NOT NULL,
  PRIMARY KEY (`MA_HANG`),
  KEY `MA_SANPHAM` (`MA_SANPHAM`),
  CONSTRAINT `tonkho_ibfk_1` FOREIGN KEY (`MA_SANPHAM`) REFERENCES `sanpham` (`MA_SANPHAM`) ON DELETE CASCADE,
  CONSTRAINT `tonkho_chk_1` CHECK ((`SOLUONG` >= 0)),
  CONSTRAINT `tonkho_chk_2` CHECK ((`DON_GIA` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `xuat_hang` (
  `IDXH` int NOT NULL AUTO_INCREMENT,
  `MA_PHIEU_XUAT` varchar(20) NOT NULL,
  `MA_NCC` varchar(20) NOT NULL,
  `NGUOI_TAO` varchar(50) NOT NULL,
  `SOLUONG` int DEFAULT NULL,
  `DON_GIA` decimal(15,2) DEFAULT NULL,
  `THOI_GIAN_TAO` datetime NOT NULL,
  `TONG_TIEN` decimal(15,2) DEFAULT NULL,
  `MA_SANPHAM` varchar(20) NOT NULL,
  `TEN_SANPHAM` varchar(40) NOT NULL,
  PRIMARY KEY (`IDXH`),
  KEY `MA_SANPHAM` (`MA_SANPHAM`),
  KEY `idx_ma_ncc` (`MA_NCC`),
  CONSTRAINT `xuat_hang_ibfk_1` FOREIGN KEY (`MA_SANPHAM`) REFERENCES `sanpham` (`MA_SANPHAM`) ON DELETE CASCADE,
  CONSTRAINT `xuat_hang_ibfk_2` FOREIGN KEY (`MA_NCC`) REFERENCES `nhacungcap` (`MA_NCC`) ON DELETE CASCADE,
  CONSTRAINT `xuat_hang_chk_1` CHECK ((`SOLUONG` >= 0)),
  CONSTRAINT `xuat_hang_chk_2` CHECK ((`DON_GIA` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO SANPHAM (MA_SANPHAM, TEN_SANPHAM, DON_GIA, XUAT_XU, LOAI_SANPHAM, SOLUONG) VALUES
('SP001', 'Điện thoại iPhone 15', 35000000, 'Mỹ', 'Điện tử', 20),
('SP002', 'Laptop Dell XPS 13', 45000000, 'Mỹ', 'Máy tính', 10),
('SP003', 'Tivi Sony 55 inch', 25000000, 'Nhật Bản', 'Điện tử gia dụng', 15),
('SP004', 'Máy giặt LG Inverter', 12000000, 'Hàn Quốc', 'Gia dụng', 12),
('SP005', 'Quạt điện Panasonic', 1500000, 'Nhật Bản', 'Gia dụng', 50),
('SP006', 'Bàn phím cơ Razer', 3500000, 'Trung Quốc', 'Phụ kiện máy tính', 25),
('SP007', 'Chuột Logitech MX Master 3', 2500000, 'Thụy Sĩ', 'Phụ kiện máy tính', 30),
('SP008', 'Máy ảnh Canon EOS R6', 65000000, 'Nhật Bản', 'Máy ảnh', 5),
('SP009', 'Máy hút bụi Xiaomi', 5000000, 'Trung Quốc', 'Gia dụng', 40),
('SP010', 'Tai nghe Sony WH-1000XM5', 8000000, 'Nhật Bản', 'Âm thanh', 18),
('SP011', 'Laptop Acer Aspire 5', 15000.00, 'Việt Nam', 'Máy tính xách tay', 10),
('SP012', 'Điện thoại iPhone 12', 25000.00, 'Mỹ', 'Điện thoại di động', 20),
('SP013', 'Tai nghe Sony WH-1000XM4', 5000.00, 'Nhật Bản', 'Thiết bị âm thanh', 15),
('SP014', 'Máy ảnh Canon EOS 1500D', 12000.00, 'Nhật Bản', 'Máy ảnh', 5),
('SP015', 'Smartwatch Samsung Galaxy Watch 4', 7000.00, 'Hàn Quốc', 'Đồng hồ thông minh', 25),
('SP016', 'Tablet Samsung Galaxy Tab S6', 18000.00, 'Hàn Quốc', 'Máy tính bảng', 30),
('SP017', 'Loa JBL Charge 4', 3000.00, 'Mỹ', 'Thiết bị âm thanh', 35),
('SP018', 'Router TP-Link Archer C6', 1500.00, 'Trung Quốc', 'Thiết bị mạng', 40),
('SP019', 'Chuột Logitech MX Master 3', 2000.00, 'Thụy Sĩ', 'Phụ kiện máy tính', 45),
('SP020', 'Bàn phím cơ Razer BlackWidow', 4000.00, 'Mỹ', 'Phụ kiện máy tính', 50),
('SP021', 'Ổ cứng SSD Samsung 970 Evo', 3500.00, 'Hàn Quốc', 'Lưu trữ', 55),
('SP022', 'Máy in HP LaserJet Pro', 6000.00, 'Mỹ', 'Thiết bị văn phòng', 60),
('SP023', 'Camera an ninh Xiaomi Mi 360', 1500.00, 'Trung Quốc', 'Thiết bị an ninh', 65),
('SP024', 'Smart TV LG 4K UHD', 25000.00, 'Hàn Quốc', 'Thiết bị gia dụng', 10),
('SP025', 'Điều hòa Daikin Inverter', 20000.00, 'Nhật Bản', 'Thiết bị gia dụng', 15),
('SP026', 'Nồi cơm điện Philips HD3038', 2000.00, 'Hà Lan', 'Thiết bị nhà bếp', 70),
('SP027', 'Bếp từ Panasonic', 5000.00, 'Nhật Bản', 'Thiết bị nhà bếp', 75),
('SP028', 'Máy xay sinh tố Philips HR2118', 1500.00, 'Hà Lan', 'Thiết bị nhà bếp', 80),
('SP029', 'Quạt điều hòa Sunhouse SHD7727', 3500.00, 'Việt Nam', 'Thiết bị gia dụng', 85),
('SP030', 'Tủ lạnh Toshiba Inverter', 15000.00, 'Nhật Bản', 'Thiết bị gia dụng', 20),
('SP031', 'Máy giặt LG Inverter', 12000.00, 'Hàn Quốc', 'Thiết bị gia dụng', 25),
('SP032', 'Micro không dây Shure SM58', 3000.00, 'Mỹ', 'Thiết bị âm thanh', 90),
('SP033', 'Máy chiếu Epson EB-X05', 6000.00, 'Nhật Bản', 'Thiết bị văn phòng', 30),
('SP034', 'Đèn bàn Xiaomi Yeelight', 500.00, 'Trung Quốc', 'Thiết bị điện', 40),
('SP035', 'Ổ cắm điện thông minh Xiaomi', 300.00, 'Trung Quốc', 'Thiết bị điện', 45),
('SP036', 'Sạc dự phòng Anker PowerCore', 2000.00, 'Mỹ', 'Phụ kiện di động', 100),
('SP037', 'Pin sạc Eneloop', 400.00, 'Nhật Bản', 'Phụ kiện di động', 110),
('SP038', 'Đèn ngủ thông minh Xiaomi', 200.00, 'Trung Quốc', 'Thiết bị điện', 70),
('SP039', 'Máy lọc không khí Sharp', 5000.00, 'Nhật Bản', 'Thiết bị gia dụng', 20),
('SP040', 'Robot hút bụi Xiaomi Roborock', 7000.00, 'Trung Quốc', 'Thiết bị gia dụng', 30);
INSERT INTO NHACUNGCAP (MA_NCC, TEN_NHACUNGCAP, SODIENTHOAI, DIACHI, EMAIL) VALUES
('NCC001', 'Công ty TNHH Điện Máy Xanh', '0901234567', '123 Nguyễn Văn Cừ, TP.HCM', 'dienmayxanh@gmail.com'),
('NCC002', 'Công ty CP Thế Giới Di Động', '0912345678', '456 Lý Thường Kiệt, TP.HCM', 'tgdd@gmail.com'),
('NCC003', 'Công ty TNHH LG Việt Nam', '0923456789', '789 Cách Mạng Tháng 8, Hà Nội', 'lgvietnam@lg.com'),
('NCC004', 'Công ty TNHH Sony Electronics', '0934567890', '101 Hai Bà Trưng, Đà Nẵng', 'sony@sony.com'),
('NCC005', 'Công ty TNHH Samsung Việt Nam', '0945678901', '202 Trần Hưng Đạo, TP.HCM', 'samsungvietnam@samsung.com'),
('NCC006', 'Công ty TNHH Canon Việt Nam', '0956789012', '303 Lê Lợi, Hà Nội', 'canon@canon.com'),
('NCC007', 'Công ty TNHH Logitech Việt Nam', '0967890123', '404 Phạm Văn Đồng, TP.HCM', 'logitechvietnam@logitech.com'),
('NCC008', 'Công ty CP Xiaomi Việt Nam', '0978901234', '505 Võ Thị Sáu, Hà Nội', 'xiaomi@xiaomi.com'),
('NCC009', 'Công ty TNHH Panasonic Việt Nam', '0989012345', '606 Quang Trung, Đà Nẵng', 'panasonicvietnam@panasonic.com'),
('NCC010', 'Công ty TNHH Razer Việt Nam', '0990123456', '707 Nguyễn Huệ, TP.HCM', 'razer@razer.com');
