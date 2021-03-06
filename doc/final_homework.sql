-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-12-23 10:04:58
-- 服务器版本： 5.7.26-log
-- PHP 版本： 7.0.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `final_homework`
--

-- --------------------------------------------------------

--
-- 表的结构 `blog`
--

CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `gmtCreate` bigint(20) DEFAULT NULL,
  `gmtModified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `commentCount` int(11) DEFAULT '0',
  `tag` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `blog`
--

INSERT INTO `blog` (`id`, `title`, `content`, `gmtCreate`, `gmtModified`, `creator`, `commentCount`, `tag`) VALUES
(1, '嘿嘿hi', 'asdasdd', 1602686145802, 1602686145802, 1, 1, '嗯嗯嗯'),
(2, 'admin的第一次发言', 'adminadmin', 1602687282412, 1602687282412, 2, 2, 'admin'),
(3, 'asdasdasd', 'asdasdasdasda', 1603194682736, 1603194682736, 3, 0, 'asdasd');

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(50) DEFAULT NULL,
  `blogId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `content`, `blogId`, `userId`) VALUES
(1, '回复', 1, 1),
(2, 'fgsdfsdfs', 1, 1),
(8, 'adminadiminadmimn', 2, 2),
(9, 'asdasdasdasdasdasdas', 2, 2),
(10, 'asdasdasdasdasdas', 1, 3);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `gmtCreate` bigint(20) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  `bio` varchar(256) NOT NULL DEFAULT '什么都没有'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `gmtCreate`, `isAdmin`, `bio`) VALUES
(1, '123456@qq.com', 'zhazha', '$2a$10$gZ0c720DA4BXYgQ64Wb4EOXfWx/DE0KdPrKl/C37bxJJHfL4WDRiO', 1602686120326, 0, '这个人没写呢'),
(2, 'admin@admin.com', 'admin', '$2a$10$pajZQr.O/k6CSVTTHeytjuZR2sJc.FBM18fGPnGfD7U6JjcNjvaCC', 1602687257669, 1, '这个人没写呢'),
(3, 'aaa@aaa.com', 'aaaaaa', '$2a$10$qOdqiK165LoH55fWp/Ow8ePgbPzobVHKR/E16ySD/xKVm.YO.dnXi', 1603194668235, 0, '这个人没写呢'),
(4, 'a', 'a', '$2a$10$huFzC6IqxRVLKtXoPOEnDu9MMCCugeKTbo5QuG8S/OD9OZtvrFZvi', 1608688929217, 0, '这个人没写呢'),
(5, '123@123.com', 'abcabc', '$2a$10$SEHUsCX/VMBrLY5IkgDnnuVGLLE2OeTvPZJ4nX4.Zw7WpvAsSKuye', 1608688963221, 0, '这个人没写呢');

--
-- 转储表的索引
--

--
-- 表的索引 `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`),
  ADD KEY `blog_user_id_fk` (`creator`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comment_blog_id_fk` (`blogId`),
  ADD KEY `comment_user_id_fk` (`userId`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `blog`
--
ALTER TABLE `blog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 限制导出的表
--

--
-- 限制表 `blog`
--
ALTER TABLE `blog`
  ADD CONSTRAINT `blog_user_id_fk` FOREIGN KEY (`creator`) REFERENCES `user` (`id`);

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_blog_id_fk` FOREIGN KEY (`blogId`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_user_id_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
