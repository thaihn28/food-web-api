-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th4 30, 2022 lúc 05:51 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `e-commercedb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `is_approve` bit(1) NOT NULL,
  `phone_no` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` double NOT NULL,
  `username` varchar(255) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`id`, `address`, `date`, `is_approve`, `phone_no`, `quantity`, `total`, `username`, `product_id`) VALUES
(1, 'Ha Noi', '2022-04-16 21:10:24', b'0', '0242234', 1, 100, 'Thai HN', 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Breakfast'),
(2, 'Lunches'),
(3, 'Dinner'),
(4, 'Drink'),
(5, 'FastFood');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`product_id`, `description`, `image_name`, `name`, `price`, `quantity`, `category_id`) VALUES
(1, 'Bao gồm: 1 burger gà nhỏ,1 miếng gà rán,khoai tây nhỏ,1 Coca cỡ nhỏ. Đối với gà không cay: Đã bao gồm 2x Tương Cà, 1x Tương Ớt. Đối với gà cay: Đã bao gồm 2x Tương Cà', 'http://localhost:8080/productImages/mcspicy_chicken_rice.png', 'Cơm phi lê gà cay McSpicy', 76, 56, 2),
(2, '1 burger bò phô mai ,1 miếng gà rán,1 khoai tây cỡ nhỏ,1 Coca cỡ nhỏ. Đối với gà không cay: Đã bao gồm 2x Tương Cà, 1x Tương Ớt. Đối với gà cay: Đã bao gồm 2x Tương Cà', 'http://localhost:8080/productImages/foody-upload-api-foody-mobile-com-ga-hoi-an-5-200617094945.jpeg', 'Cơm Cánh Gà Chiên Mắm + Cánh Gà Chiên Mắm', 110, 50, 2),
(3, 'Bao gồm:1 burger phi-lê cá,1 miếng gà rán,1 khoai cỡ nhỏ,1 coca cỡ nhỏ. Đối với gà không cay: Đã bao gồm 2x Tương Cà, 1x Tương Ớt. Đối với gà cay: Đã bao gồm 2x Tương Cà', 'http://localhost:8080/productImages/foody-mobile-2933-jpg-736-636076540248109503.jpeg', 'Bún Ngan Trộn', 35, 99, 2),
(4, 'Bao gồm: 1 burger bò phô mai,1 miếng gà rán,1 Coca cỡ Vừa. Đối với gà không cay: Đã bao gồm 1x Tương Cà, 1x Tương Ớt. Đối với gà cay: Đã bao gồm 1x Tương Cà.', 'http://localhost:8080/productImages/foody-mobile-gd1-jpg-211-635576103874383334.jpeg', 'Cơm Sườn Chua Ngọt', 50, 100, 2),
(5, 'Bao gồm: 3 miếng gà rán, 4 miếng gà McNuggets, 1 khoai cỡ lớn,2 Coca cỡ vừa. Đối với gà không cay kèm khoai: Đã bao gồm 3x Tương Cà, 1x Tương Ớt. Đối với gà cay kèm khoai: Đã bao gồm 3x Tương Cà.', 'http://localhost:8080/productImages/image-e856977a-200910115811.jpeg', 'BÁNH ĐA CUA BÒ GIÒ ĐẬU CHẢ CÁ', 62, 99, 2),
(6, 'Bánh mỳ doner kebab since 2012 ( quý khách ghi chú bánh : giòn hay mềm , có nhiều hay ít ( tương cà + tương ớt + sốt mayone + rau sống xà lách , mùi ) . Ko ghi chú gì quán xin làm đầy đủ . Xin cảm ơn!', 'http://localhost:8080/productImages/file_dcdf7ca3-2367-439f-aa65-208-83401e15-220404113715.jpeg', 'Bánh Mỳ Donner Kebab', 25, 99, 1),
(7, '1 mì + trứng + rau củ + nem chua + khoo heo cháy tỏi', 'http://localhost:8080/productImages/474b60dd-33d7-4fc5-b6f1-4bf51010-2564ecd3-211119213441.jpeg', 'Mì Trộn Thập Cẩm Đặc Biệt', 35, 99, 1),
(8, 'Indomie 158 - Thái Thịnh', 'http://localhost:8080/productImages/file_restaurant_photo_ml9d_16212-bf0dcbeb-210517102640.jpeg', 'Mì Trộn', 40, 99, 1),
(9, 'GỒM: TRÂN CHÂU TRẮNG hoặc ĐEN ( tùy vào tình trạng tại cửa hàng)', 'http://localhost:8080/productImages/foody-upload-api-foody-mobile-fo-1536821f-211215091201.jpeg', 'Trà Sữa Heytea', 20, 99, 4),
(10, '8 Nguyễn Xiển, Thanh Xuân, Hà Nội', 'http://localhost:8080/productImages/8dfa1134-bfe6-47f9-bfef-243bf02234e3.jpeg', 'Trà Sữa Ba Anh Em', 34, 99, 4),
(11, 'CT2DN1 Khu đô thị Định Công, P. Định Công, Hoàng Mai, Hà Nội', 'http://localhost:8080/productImages/file_restaurant_photo_g9e7_16486-592c502b-220330162702.jpeg', 'Sinh Tố & Nước Ép Nguyên Chất', 32, 99, 4),
(12, 'CT2DN1 Khu đô thị Định Công, P. Định Công, Hoàng Mai, Hà Nội', 'http://localhost:8080/productImages/file_restaurant_photo_wmdy_16235-41916384-210613224528.jpeg', 'Linh Đan Mỳ Trộn', 40, 99, 5),
(13, '310 P. Chùa Quỳnh, P. Quỳnh Lôi, Hai Bà Trưng, Hà Nội', 'http://localhost:8080/productImages/file_restaurant_photo_wnh1_16217-295629e0-210523024335.jpeg', 'Gà Rán, Bánh Gà & Đồ Ăn Vặt', 55, 99, 5),
(14, '45 Ngõ 20 Mỹ Đình, Nam Từ Liêm, Hà Nội', 'http://localhost:8080/productImages/file_restaurant_photo_voek_16259-742d84d0-210710162637.jpeg', 'Lẩu Tokbokki, Mỳ Cay, Trà Chanh', 67, 99, 5),
(15, '20 Ngõ 592 Trường Chinh, Đống Đa, Hà Nội', 'http://localhost:8080/productImages/image-324e2e15-200910115811.jpeg', 'Chân Gà Sả Ớt', 45, 99, 5),
(16, '2 Ngõ 402/28 Mỹ Đình, Đình Thôn, P. Mỹ Đình 1, Nam Từ Liêm, Hà Nội', 'http://localhost:8080/productImages/foody-upload-api-foody-mobile-z3-b77433e3-220107133722.jpeg', 'Chè thái thập cẩm', 45, 99, 5),
(17, '288 Xuân Đỉnh, Bắc Từ Liêm, Hà Nội', 'http://localhost:8080/productImages/file_restaurant_photo_qv6v_16471-0191309f-220313222905.jpeg', 'COMBO 2 ĐÙI GÀ RÁN + 2 Coca', 54, 99, 5),
(18, '38 Mã Mây, Hoàn Kiếm, Hà Nội', 'http://localhost:8080/productImages/image-1142eab8-200910115828.jpeg', 'Cơm Rang Dưa Bò', 45, 99, 3),
(19, '2C Tống Duy Tân, P. Hàng Bông, Hoàn Kiếm, Hà Nội', 'http://localhost:8080/productImages/foody-upload-api-foody-mobile-co-1b01379b-210525175918.jpeg', 'Bún Riêu Tóp Mỡ Mọc Giòn', 120, 99, 3),
(20, '76 Ngọc Khánh, Ba Đình, Hà Nội', 'http://localhost:8080/productImages/foody-mobile-15027746_11411011326-749-636227767870908319.jpeg', 'Lẩu Nướng & Các Món Nhậu', 150, 99, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `appointment_hour` time DEFAULT NULL,
  `appointment_time` datetime DEFAULT NULL,
  `client_name` varchar(255) NOT NULL,
  `complete` bit(1) NOT NULL,
  `date` datetime DEFAULT NULL,
  `is_approve` bit(1) NOT NULL,
  `note` longtext DEFAULT NULL,
  `phone_number` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reservation_detail`
--

CREATE TABLE `reservation_detail` (
  `id` bigint(20) NOT NULL,
  `number_of_tables` int(11) DEFAULT NULL,
  `reservstion_id` bigint(20) DEFAULT NULL,
  `table_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `table`
--

CREATE TABLE `table` (
  `id` bigint(20) NOT NULL,
  `remaining_table` int(11) DEFAULT NULL,
  `table_types` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`) VALUES
(1, 'thaihn@gmail.com', 'Thai', 'Hoang', '$2a$10$SXqm0T.xVf2xlBdRNOCxO.0IGywLDT.fWgj.2EAlzu4V9oOjYHpF2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKibn29wr9arrvnn4l3963vj5on` (`product_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `FK5drd4x1gskipdc846gum9yc5j` (`category_id`);

--
-- Chỉ mục cho bảng `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `reservation_detail`
--
ALTER TABLE `reservation_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1hlwtqlbn2n1cujv0gsnnghr` (`reservstion_id`),
  ADD KEY `FK6f334jvllai3j2641fg49ua45` (`table_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `table`
--
ALTER TABLE `table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_cn0ytsk110cbvmp3mnfmhqh1s` (`table_types`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKoshmjvr6wht0bg9oivn75aajr` (`email`);

--
-- Chỉ mục cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKjticpblwpgkc74xs0dvirhdtr` (`role_id`),
  ADD KEY `FKnn3hfs03dgsqlunrq2jirdklr` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `reservation_detail`
--
ALTER TABLE `reservation_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `table`
--
ALTER TABLE `table`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FKibn29wr9arrvnn4l3963vj5on` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK5drd4x1gskipdc846gum9yc5j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `reservation_detail`
--
ALTER TABLE `reservation_detail`
  ADD CONSTRAINT `FK1hlwtqlbn2n1cujv0gsnnghr` FOREIGN KEY (`reservstion_id`) REFERENCES `reservation` (`id`),
  ADD CONSTRAINT `FK6f334jvllai3j2641fg49ua45` FOREIGN KEY (`table_id`) REFERENCES `table` (`id`);

--
-- Các ràng buộc cho bảng `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKjticpblwpgkc74xs0dvirhdtr` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKnn3hfs03dgsqlunrq2jirdklr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
