/*
 Navicat Premium Data Transfer

 Source Server         : eric
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : twitterchat

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 29/03/2022 11:55:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `a_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `b_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` char(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` char(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NOT NULL,
  `content` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `photo` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `like_num` int NOT NULL,
  `reply_num` int NOT NULL,
  `forward_num` int NOT NULL,
  `is_reply` int NOT NULL,
  `from_message` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `host_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `host_message_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `replyer_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `replyer_message_id` char(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time` datetime NOT NULL,
  `del` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
