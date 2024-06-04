/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : springboot4r3y8

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 25/05/2024 17:55:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `isdefault` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716629497354 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '2021-04-10 12:28:34', 1, '宇宙银河系金星1号', '金某', '13823888881', '是');
INSERT INTO `address` VALUES (2, '2021-04-10 12:28:34', 2, '宇宙银河系木星1号', '木某', '13823888882', '是');
INSERT INTO `address` VALUES (3, '2021-04-10 12:28:34', 3, '宇宙银河系水星1号', '水某', '13823888883', '是');
INSERT INTO `address` VALUES (4, '2021-04-10 12:28:34', 4, '宇宙银河系火星1号', '火某', '13823888884', '是');
INSERT INTO `address` VALUES (5, '2021-04-10 12:28:34', 5, '宇宙银河系土星1号', '土某', '13823888885', '是');
INSERT INTO `address` VALUES (6, '2021-04-10 12:28:34', 6, '宇宙银河系月球1号', '月某', '13823888886', '是');
INSERT INTO `address` VALUES (1618033879807, '2021-04-10 13:51:19', 1618033830452, '广东省佛山市禅城区祖庙街道季华五路金海广场', '陈一', '12312312312', '是');
INSERT INTO `address` VALUES (1716558891285, '2024-05-24 21:54:50', 11, '冯氏家宅', '是', '15122667676', '是');
INSERT INTO `address` VALUES (1716629497353, '2024-05-25 17:31:36', 1716627711175, '博物馆', '1', '15122667676', '是');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `goodid` bigint(0) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `buynumber` int(0) NOT NULL COMMENT '购买数量',
  `price` float NULL DEFAULT NULL COMMENT '单价',
  `discountprice` float NULL DEFAULT NULL COMMENT '会员价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033994188 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `adminid` bigint(0) NULL DEFAULT NULL COMMENT '管理员id',
  `ask` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '提问',
  `reply` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复',
  `isreply` int(0) NULL DEFAULT NULL COMMENT '是否回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618034062104 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线客服' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (71, '2021-04-10 12:28:34', 1, 1, '提问1', '回复1', 1);
INSERT INTO `chat` VALUES (72, '2021-04-10 12:28:34', 2, 2, '提问2', '回复2', 2);
INSERT INTO `chat` VALUES (73, '2021-04-10 12:28:34', 3, 3, '提问3', '回复3', 3);
INSERT INTO `chat` VALUES (74, '2021-04-10 12:28:34', 4, 4, '提问4', '回复4', 4);
INSERT INTO `chat` VALUES (75, '2021-04-10 12:28:34', 5, 5, '提问5', '回复5', 5);
INSERT INTO `chat` VALUES (76, '2021-04-10 12:28:34', 6, 6, '提问6', '回复6', 6);
INSERT INTO `chat` VALUES (1618034020878, '2021-04-10 13:53:40', 1618033830452, NULL, '请问有西装外套吗', NULL, 0);
INSERT INTO `chat` VALUES (1618034062103, '2021-04-10 13:54:21', 1618033830452, 1, NULL, '有', NULL);

-- ----------------------------
-- Table structure for chima
-- ----------------------------
DROP TABLE IF EXISTS `chima`;
CREATE TABLE `chima`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `chima` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '尺码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `chima`(`chima`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033687650 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '尺码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chima
-- ----------------------------
INSERT INTO `chima` VALUES (41, '2021-04-10 12:28:34', '尺码1');
INSERT INTO `chima` VALUES (42, '2021-04-10 12:28:34', '尺码2');
INSERT INTO `chima` VALUES (43, '2021-04-10 12:28:34', '尺码3');
INSERT INTO `chima` VALUES (44, '2021-04-10 12:28:34', '尺码4');
INSERT INTO `chima` VALUES (45, '2021-04-10 12:28:34', '尺码5');
INSERT INTO `chima` VALUES (46, '2021-04-10 12:28:34', '尺码6');
INSERT INTO `chima` VALUES (1618033674668, '2021-04-10 13:47:54', 'XS(160/80A)');
INSERT INTO `chima` VALUES (1618033687649, '2021-04-10 13:48:06', '34(165/64A)');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'picture1', 'http://localhost:9999/springboot4r3y8/upload/1716623980555.jpg');

-- ----------------------------
-- Table structure for dingdanpingjia
-- ----------------------------
DROP TABLE IF EXISTS `dingdanpingjia`;
CREATE TABLE `dingdanpingjia`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `dingdanbianhao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `shangpinmingcheng` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类',
  `pingfen` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分',
  `tianjiatupian` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加图片',
  `pingjianeirong` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评价内容',
  `pingjiariqi` date NULL DEFAULT NULL COMMENT '评价日期',
  `yonghuming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `lianxidianhua` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '否' COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '审核回复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716617401513 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dingdanpingjia
-- ----------------------------
INSERT INTO `dingdanpingjia` VALUES (1716629697360, '2024-05-25 17:35:08', '20245251731497247752', '蜻蜓队长1', NULL, '3', 'http://localhost:9999/springboot4r3y8/upload/1716629347851.jpg', '1', '2024-05-25', 'zhangsan', '15122667676', '否', NULL);
INSERT INTO `dingdanpingjia` VALUES (1716629880005, '2024-05-25 17:37:59', '20245251731497247752', '蜻蜓队长1', NULL, '1', 'http://localhost:9999/springboot4r3y8/upload/1716629347851.jpg', NULL, '2024-05-25', 'zhangsan', '15122667676', '否', NULL);
INSERT INTO `dingdanpingjia` VALUES (1716629916524, '2024-05-25 17:38:36', '202452517323639104062', '蜻蜓队长', NULL, '1', 'http://localhost:9999/springboot4r3y8/upload/1716627873365.jpg', NULL, '2024-05-25', 'zhangsan', '15122667676', '否', NULL);
INSERT INTO `dingdanpingjia` VALUES (1716629926718, '2024-05-25 17:38:46', '202452517323639104062', '蜻蜓队长', NULL, '1', 'http://localhost:9999/springboot4r3y8/upload/1716627873365.jpg', '1', '2024-05-25', 'zhangsan', '15122667676', '否', NULL);

-- ----------------------------
-- Table structure for discussshangpinxinxi
-- ----------------------------
DROP TABLE IF EXISTS `discussshangpinxinxi`;
CREATE TABLE `discussshangpinxinxi`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(0) NOT NULL COMMENT '关联表id',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussshangpinxinxi
-- ----------------------------
INSERT INTO `discussshangpinxinxi` VALUES (131, '2021-04-10 12:28:35', 1, 1, '用户名1', '评论内容1', '回复内容1');
INSERT INTO `discussshangpinxinxi` VALUES (132, '2021-04-10 12:28:35', 2, 2, '用户名2', '评论内容2', '回复内容2');
INSERT INTO `discussshangpinxinxi` VALUES (133, '2021-04-10 12:28:35', 3, 3, '用户名3', '评论内容3', '回复内容3');
INSERT INTO `discussshangpinxinxi` VALUES (134, '2021-04-10 12:28:35', 4, 4, '用户名4', '评论内容4', '回复内容4');
INSERT INTO `discussshangpinxinxi` VALUES (135, '2021-04-10 12:28:35', 5, 5, '用户名5', '评论内容5', '回复内容5');
INSERT INTO `discussshangpinxinxi` VALUES (136, '2021-04-10 12:28:35', 6, 6, '用户名6', '评论内容6', '回复内容6');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `introduction` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033809620 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品资讯' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (121, '2021-04-10 12:28:34', '标题1', '简介1', 'http://localhost:8080/springboot4r3y8/upload/1618033301322.jpg', '<p>内容1</p>');
INSERT INTO `news` VALUES (122, '2021-04-10 12:28:34', '标题2', '简介2', 'http://localhost:8080/springboot4r3y8/upload/1618033309606.jpg', '<p>内容2</p>');
INSERT INTO `news` VALUES (123, '2021-04-10 12:28:34', '标题3', '简介3', 'http://localhost:8080/springboot4r3y8/upload/1618033317928.jpg', '<p>内容3</p>');
INSERT INTO `news` VALUES (124, '2021-04-10 12:28:34', '标题4', '简介4', 'http://localhost:8080/springboot4r3y8/upload/1618033328182.jpg', '<p>内容4</p>');
INSERT INTO `news` VALUES (125, '2021-04-10 12:28:34', '标题5', '简介5', 'http://localhost:8080/springboot4r3y8/upload/1618033335925.jpg', '<p>内容5</p>');
INSERT INTO `news` VALUES (126, '2021-04-10 12:28:34', '标题6', '简介6', 'http://localhost:8080/springboot4r3y8/upload/1618033347394.jpg', '<p>内容6</p>');
INSERT INTO `news` VALUES (1618033809619, '2021-04-10 13:50:09', '服装优惠资讯', '服装优惠活动', 'http://localhost:8080/springboot4r3y8/upload/1618033781471.jpg', '<p>全场服装参与618优惠活动，具体详见商品详情页</p><p><img src=\"http://localhost:8080/springboot4r3y8/upload/1618033807231.jpg\"></p>');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `orderid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `goodid` bigint(0) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `buynumber` int(0) NOT NULL COMMENT '购买数量',
  `price` float NOT NULL DEFAULT 0 COMMENT '价格/积分',
  `discountprice` float NULL DEFAULT 0 COMMENT '折扣价格',
  `total` float NOT NULL DEFAULT 0 COMMENT '总价格/总积分',
  `discounttotal` float NULL DEFAULT 0 COMMENT '折扣总价格',
  `type` int(0) NULL DEFAULT 1 COMMENT '支付类型',
  `status` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderid`(`orderid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716558911641 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1716629509554, '2024-05-25 17:31:49', '20245251731497247752', 'shangpinxinxi', 1716627711175, 1716629356804, '蜻蜓队长1', 'http://localhost:9999/springboot4r3y8/upload/1716629347851.jpg', 1, 10, 10, 10, 10, 1, '已支付', '博物馆', '15122667676', '1');
INSERT INTO `orders` VALUES (1716629536783, '2024-05-25 17:32:16', '202452517321636680990', 'shangpinxinxi', 1716627711175, 1716629356804, '蜻蜓队长1', 'http://localhost:9999/springboot4r3y8/upload/1716629347851.jpg', 1, 10, 10, 10, 10, 1, '已支付', '博物馆', '15122667676', '1');
INSERT INTO `orders` VALUES (1716629557335, '2024-05-25 17:32:36', '202452517323639104062', 'shangpinxinxi', 1716627711175, 1716627895568, '蜻蜓队长', 'http://localhost:9999/springboot4r3y8/upload/1716627873365.jpg', 1, 100, 100, 100, 100, 1, '已支付', '博物馆', '15122667676', '1');
INSERT INTO `orders` VALUES (1716629564956, '2024-05-25 17:32:44', '20245251732444180852', 'shangpinxinxi', 1716627711175, 1716627895568, '蜻蜓队长', 'http://localhost:9999/springboot4r3y8/upload/1716627873365.jpg', 1, 100, 100, 100, 100, 1, '已支付', '博物馆', '15122667676', '1');

-- ----------------------------
-- Table structure for shangjia
-- ----------------------------
DROP TABLE IF EXISTS `shangjia`;
CREATE TABLE `shangjia`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `yonghuming` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mima` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `xingming` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `touxiang` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `xingbie` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `lianxidianhua` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `yingyezhizhao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `yinhangzhanghao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shenfenzheng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sfsh` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangjia
-- ----------------------------
INSERT INTO `shangjia` VALUES (2, 'lisi', '123', '李四', 'http://localhost:9999/springboot4r3y8/upload/1716627814833.jpg', '男', '15122667676', 'http://localhost:9999/springboot4r3y8/upload/1716627817648.jpg', '11111111111111', 'http://localhost:9999/springboot4r3y8/upload/1716627821717.jpg', '是');

-- ----------------------------
-- Table structure for shangpinfenlei
-- ----------------------------
DROP TABLE IF EXISTS `shangpinfenlei`;
CREATE TABLE `shangpinfenlei`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品分类',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `shangpinfenlei`(`shangpinfenlei`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033648163 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinfenlei
-- ----------------------------
INSERT INTO `shangpinfenlei` VALUES (21, '2021-04-10 12:28:34', '商品分类1');
INSERT INTO `shangpinfenlei` VALUES (22, '2021-04-10 12:28:34', '商品分类2');
INSERT INTO `shangpinfenlei` VALUES (23, '2021-04-10 12:28:34', '商品分类3');
INSERT INTO `shangpinfenlei` VALUES (24, '2021-04-10 12:28:34', '商品分类4');
INSERT INTO `shangpinfenlei` VALUES (25, '2021-04-10 12:28:34', '商品分类5');
INSERT INTO `shangpinfenlei` VALUES (26, '2021-04-10 12:28:34', '商品分类6');
INSERT INTO `shangpinfenlei` VALUES (1618033642179, '2021-04-10 13:47:21', '卫衣');
INSERT INTO `shangpinfenlei` VALUES (1618033648162, '2021-04-10 13:47:27', '牛仔裤');

-- ----------------------------
-- Table structure for shangpinxinxi
-- ----------------------------
DROP TABLE IF EXISTS `shangpinxinxi`;
CREATE TABLE `shangpinxinxi`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `shangpinmingcheng` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `shangpinfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品分类',
  `tupian` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `pinpai` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `chima` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '尺码',
  `shangpinxiangqing` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品详情',
  `clicktime` datetime(0) NULL DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(0) NULL DEFAULT 0 COMMENT '点击次数',
  `price` float NOT NULL COMMENT '价格',
  `sjid` bigint(0) NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用说明',
  `sfyxyj` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否允许议价',
  `spsl` int(0) NULL DEFAULT 0 COMMENT '商品数量',
  `xjcd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新旧程度',
  `hpl` double NULL DEFAULT 100 COMMENT '好评率',
  `xiaoliang` double NULL DEFAULT 0 COMMENT '销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716553624839 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shangpinxinxi
-- ----------------------------
INSERT INTO `shangpinxinxi` VALUES (1716627895568, '2024-05-25 17:04:55', '蜻蜓队长', '商品分类1', 'http://localhost:9999/springboot4r3y8/upload/1716627873365.jpg', NULL, '浅蓝色', NULL, NULL, '2024-05-25 17:32:44', 5, 100, 2, NULL, '是', 98, '全新', 25, 2);
INSERT INTO `shangpinxinxi` VALUES (1716629356804, '2024-05-25 17:29:16', '蜻蜓队长1', '商品分类1', 'http://localhost:9999/springboot4r3y8/upload/1716629347851.jpg', NULL, '浅蓝色', NULL, NULL, '2024-05-25 17:52:15', 9, 10, 2, NULL, NULL, 98, NULL, 25, 2);
INSERT INTO `shangpinxinxi` VALUES (1716629374897, '2024-05-25 17:29:34', '蜻蜓队长2', '商品分类1', NULL, NULL, '浅蓝色', NULL, NULL, NULL, 0, 156, 2, NULL, NULL, 12, NULL, 100, 0);

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `refid` bigint(0) NULL DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033998103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storeup
-- ----------------------------

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(0) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `expiratedtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 11, '用户1', 'yonghu', '用户', 'otmc5fsnlt0vrplq46l3o3x9s3g7natl', '2021-04-10 12:30:16', '2024-05-25 17:47:13');
INSERT INTO `token` VALUES (2, 1, 'abo', 'users', '管理员', '678xr4g96909qol2im3kv3qg7zvnslik', '2021-04-10 13:39:08', '2024-05-25 18:03:47');
INSERT INTO `token` VALUES (3, 1618033830452, '1', 'yonghu', '用户', 'ydygn5isqb7bvgj9m3eavmh7a2nys750', '2021-04-10 13:50:37', '2021-04-10 14:55:13');
INSERT INTO `token` VALUES (4, 1, 'abo', 'yonghu', '用户', 'al9a0l2fa4xpsh5kh3h2vi5rgkmebotb', '2024-05-23 20:17:22', '2024-05-25 17:49:03');
INSERT INTO `token` VALUES (5, 1716551877101, 'abo1', 'shangJia', '用户', '4mymlt5acdd4fhh8kjog3fuubkwjk1z6', '2024-05-24 19:58:02', '2024-05-24 20:58:03');
INSERT INTO `token` VALUES (6, 1716551763562, 'abo', 'yonghu', '用户', 'f646jeboj8wt21l6udrhnb6kbpepzp3f', '2024-05-24 22:16:49', '2024-05-25 17:13:30');
INSERT INTO `token` VALUES (7, 1716627344344, 'zhangsan', 'yonghu', '用户', 'doo3iaab65u0dp31xq35ew4ij9rrecql', '2024-05-25 16:55:53', '2024-05-25 17:55:53');
INSERT INTO `token` VALUES (8, 1716627391307, 'abo', 'shangJia', '用户', '8grcyxln2g5pken2ayflgkp08bgyxcsd', '2024-05-25 16:56:35', '2024-05-25 17:56:36');
INSERT INTO `token` VALUES (9, 1716627711175, 'zhangsan', 'yonghu', '用户', 'svpc5nml2a9df5n54rqwufnbjwqnblag', '2024-05-25 17:01:58', '2024-05-25 18:50:32');
INSERT INTO `token` VALUES (10, 1716627749577, 'lisi', 'shangJia', '用户', 'qutszavqx3mxq3sxxt0md38m4937fboo', '2024-05-25 17:02:48', '2024-05-25 18:06:44');
INSERT INTO `token` VALUES (11, 2, 'lisi', 'shangJia', '用户', '7v0e80m0v1nlwphokm4qs0nlhwc9xvhn', '2024-05-25 17:09:55', '2024-05-25 18:54:57');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'abo', 'abo', '管理员', '2021-04-10 12:28:35');

-- ----------------------------
-- Table structure for yanse
-- ----------------------------
DROP TABLE IF EXISTS `yanse`;
CREATE TABLE `yanse`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `yanse` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '颜色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `yanse`(`yanse`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1618033660537 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '颜色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yanse
-- ----------------------------
INSERT INTO `yanse` VALUES (31, '2021-04-10 12:28:34', '颜色1');
INSERT INTO `yanse` VALUES (32, '2021-04-10 12:28:34', '颜色2');
INSERT INTO `yanse` VALUES (33, '2021-04-10 12:28:34', '颜色3');
INSERT INTO `yanse` VALUES (34, '2021-04-10 12:28:34', '颜色4');
INSERT INTO `yanse` VALUES (35, '2021-04-10 12:28:34', '颜色5');
INSERT INTO `yanse` VALUES (36, '2021-04-10 12:28:34', '颜色6');
INSERT INTO `yanse` VALUES (1618033654815, '2021-04-10 13:47:34', '浅蓝色');
INSERT INTO `yanse` VALUES (1618033660536, '2021-04-10 13:47:39', '红色');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `yonghuming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `mima` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `xingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `touxiang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `xingbie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `lianxidianhua` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `money` float NULL DEFAULT 0 COMMENT '余额',
  `yinhangzhanghao` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `youxiang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `yonghuming`(`yonghuming`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716551763563 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (1716627711175, '2024-05-25 17:01:51', 'zhangsan', '123', '张三', NULL, NULL, '15122667676', 9780, NULL, '北京', NULL);

SET FOREIGN_KEY_CHECKS = 1;
