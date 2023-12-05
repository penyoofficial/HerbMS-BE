/*
 Navicat Premium Data Transfer

 Source Server         : Local MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018 (8.0.18)
 Source Host           : localhost:3306
 Source Schema         : herbms

 Target Server Type    : MySQL
 Target Server Version : 80018 (8.0.18)
 File Encoding         : 65001

 Date: 25/11/2023 22:36:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for experiences
-- ----------------------------
DROP TABLE IF EXISTS `experiences`;
CREATE TABLE `experiences`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `herb_id` int(11) NOT NULL COMMENT '中草药 ID',
  `derivation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出处',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '心得内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `experiences::herb_id`(`herb_id` ASC) USING BTREE,
  CONSTRAINT `experiences::herb_id` FOREIGN KEY (`herb_id`) REFERENCES `herbs` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '中草药使用心得' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of experiences
-- ----------------------------
INSERT INTO `experiences` VALUES (1, 1, '《本草纲目》', '人参是一种具有补气养血、益精填髓的药材，对于气虚血弱、四肢乏力、心悸失眠等症状有很好的效果。');
INSERT INTO `experiences` VALUES (2, 1, '《神农本草经》', '人参可用于治疗气虚乏力、脾胃虚弱等症状，具有补中益气、健脾开胃的功效。');
INSERT INTO `experiences` VALUES (3, 1, '《本经逢原》', '人参具有补气固表、益肺生津的作用，适用于气虚血弱、自汗、久咳等症状的调理。');
INSERT INTO `experiences` VALUES (4, 2, '《本草纲目》', '黄芪是一种常用的中药材，具有益气固表、健脾生津的功效，适用于气虚乏力、食欲不振、久泻久痢等症状的调理。');
INSERT INTO `experiences` VALUES (5, 2, '《本经逢原》', '黄芪可用于治疗气虚乏力、脾胃虚弱等症状，具有补中益气、健脾开胃的功效。');
INSERT INTO `experiences` VALUES (6, 2, '《本草纲目》', '黄芪具有益气固表、托毒剂的作用，适用于气虚乏力、久泻久痢等症状的调理。');
INSERT INTO `experiences` VALUES (7, 3, '《本草纲目》', '白术是一种常用的中药材，具有健脾益胃、止泻固肠的作用，适用于脾胃虚弱、食欲不振、泄泻等症状的治疗。');
INSERT INTO `experiences` VALUES (8, 3, '《神农本草经》', '白术可用于治疗脾胃虚弱、食欲不振等症状，具有健脾开胃的功效。');
INSERT INTO `experiences` VALUES (9, 3, '《本经逢原》', '白术具有健脾固肾、和胃止泻的作用，适用于脾胃虚弱、泄泻等症状的调理。');

-- ----------------------------
-- Table structure for herbs
-- ----------------------------
DROP TABLE IF EXISTS `herbs`;
CREATE TABLE `herbs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `code` int(11) NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学名',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '别名',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '归属类别',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '本经原文',
  `efficacy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主治',
  `taste` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性味',
  `origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产地',
  `dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用量',
  `taboo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '禁忌',
  `processing` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '炮制方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '中草药' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of herbs
-- ----------------------------
INSERT INTO `herbs` VALUES (1, 100, '人参', '参、芝', '增补', '本品为五加科植物人参的干燥根茎。', '补气健脾，生津止渴。', '甘甜微苦，平。', '主产于东北、华北等地。', '煎服，3~9g；或入丸、散、膏剂。', '大热亢阳、阴虚火旺者忌服。', '鲜品捣烂，晒干；或鲜品晒干.');
INSERT INTO `herbs` VALUES (2, 101, '黄芪', '黄芪、黄精', '增补', '本品为豆科植物黄芪的根及根茎。', '益气固表，利水除湿。', '甘平。', '主产于陕西、甘肃、河北等地。', '煎服，6~12g；或入丸、散、膏剂。', '表证发热者忌服。', '去须、泥、杂质，洗净，晒干.');
INSERT INTO `herbs` VALUES (3, 102, '白术', '半夏、半叶', '中经', '本品为菊科植物白术的块茎。', '健脾胃，燥湿化痰。', '甘苦，温。', '主产于长江流域一带。', '煎服，3~10g；或入丸、散、膏剂。', '脾胃虚寒、腹胀便溏者忌服。', '鲜品除去泥土，洗净，切片，晒干.');
INSERT INTO `herbs` VALUES (4, 103, '甘草', '甘草、甘遂', '增补', '本品为豆科植物甘草的根及根茎。', '和中止痛，解毒润肺。', '甘平。', '主产于甘肃、内蒙古等地。', '煎服，1~5g；或入丸、散、膏剂。', '阳明热盛、阴虚发热者忌服。', '除去须根泥土，洗净，晒干.');
INSERT INTO `herbs` VALUES (5, 104, '当归', '当归、唐柏', '上经', '本品为伞形科植物当归的根。', '养血活血，调经止痛。', '辛、甘、温。', '主产于四川、陕西、甘肃等地。', '煎服，3~10g；或入丸、散、膏剂。', '孕妇慎用。', '除去泥土，洗净，切段，晒干.');
INSERT INTO `herbs` VALUES (6, 105, '枸杞', '枸杞、地骨皮', '增补', '本品为茄科植物枸杞的果实。', '滋阴补肾，明目益精。', '甘平。', '主产于宁夏、甘肃等地。', '煎服，6~12g；或入丸、散、膏剂。', '脾胃虚寒、腹胀便溏者慎用.', '除去杂质、泥土，洗净，晒干.');
INSERT INTO `herbs` VALUES (7, 106, '川芎', '川芎、茶花', '上经', '本品为伞形科植物川芎的根茎。', '活血止痛，祛风通络。', '辛、温。', '主产于四川、陕西等地。', '煎服，3~10g；或入丸、散、膏剂。', '孕妇慎用。', '去泥土、杂质，洗净，晒干.');
INSERT INTO `herbs` VALUES (8, 107, '桂枝', '桂枝、桂皮', '上经', '本品为樟科植物桂枝的干燥枝、枝皮。', '解表散寒，和胃止呕。', '辛、甘、温。', '主产于湖北、湖南等地。', '煎服，3~10g；或入丸、散、膏剂。', '外感发热者慎用.', '除去粗壳、泥土，洗净，切段，晒干.');
INSERT INTO `herbs` VALUES (9, 108, '茯苓', '茯苓、茯神', '中经', '本品为多孔菌科真菌茯苓子实体。', '利水渗湿，健脾安神。', '甘淡、平。', '主产于湖北、湖南、四川等地。', '煎服，6~15g；或入丸、散、膏剂。', '脾胃虚寒、食滞不化者慎用.', '除去外皮及泥土，洗净，晒至半干，切片，再晒干.');
INSERT INTO `herbs` VALUES (10, 109, '黄连', '黄连、龙胆', '下经', '本品为马兜铃科植物黄连的根茎。', '清热燥湿，泻火解毒。', '苦寒。', '主产于四川、广西等地。', '煎服，1~3g；或入丸、散、膏剂。', '脾胃虚寒、腹胀便溏者慎用.', '除去泥土，洗净，切片，晒干.');

-- ----------------------------
-- Table structure for item_differentiation_infos
-- ----------------------------
DROP TABLE IF EXISTS `item_differentiation_infos`;
CREATE TABLE `item_differentiation_infos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `code` int(11) NOT NULL COMMENT '编号',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `annotation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '条辨概要' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item_differentiation_infos
-- ----------------------------
INSERT INTO `item_differentiation_infos` VALUES (1, 100, '补气健脾，生津止渴', '无');
INSERT INTO `item_differentiation_infos` VALUES (2, 101, '益气固表，利水除湿', '无');
INSERT INTO `item_differentiation_infos` VALUES (3, 102, '健脾胃，燥湿化痰', '无');

-- ----------------------------
-- Table structure for item_differentiations
-- ----------------------------
DROP TABLE IF EXISTS `item_differentiations`;
CREATE TABLE `item_differentiations`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `item_differentiation_id` int(11) NOT NULL COMMENT '条辨 ID',
  `prescription_id` int(11) NOT NULL COMMENT '处方 ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_differentiations::item_differentiation_id`(`item_differentiation_id` ASC) USING BTREE,
  INDEX `item_differentiations::prescription_id`(`prescription_id` ASC) USING BTREE,
  CONSTRAINT `item_differentiations::item_differentiation_id` FOREIGN KEY (`item_differentiation_id`) REFERENCES `item_differentiation_infos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `item_differentiations::prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `prescription_infos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '条辨' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item_differentiations
-- ----------------------------
INSERT INTO `item_differentiations` VALUES (1, 1, 1, '对症');
INSERT INTO `item_differentiations` VALUES (2, 2, 2, '对症');
INSERT INTO `item_differentiations` VALUES (3, 3, 3, '对症');

-- ----------------------------
-- Table structure for prescription_infos
-- ----------------------------
DROP TABLE IF EXISTS `prescription_infos`;
CREATE TABLE `prescription_infos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '别名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '解释',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '处方概要' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of prescription_infos
-- ----------------------------
INSERT INTO `prescription_infos` VALUES (1, '补气生津方', '无', '养阴润燥，益气生津');
INSERT INTO `prescription_infos` VALUES (2, '祛湿利水方', '无', '祛湿利水，健脾利湿');
INSERT INTO `prescription_infos` VALUES (3, '调经止痛方', '无', '活血调经，舒筋止痛');

-- ----------------------------
-- Table structure for prescriptions
-- ----------------------------
DROP TABLE IF EXISTS `prescriptions`;
CREATE TABLE `prescriptions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一识别码',
  `prescription_id` int(11) NOT NULL COMMENT '中草药处方 ID',
  `herb_id` int(11) NOT NULL COMMENT '中草药 ID',
  `dosage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用量',
  `usage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用法',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `prescriptions::prescription_id`(`prescription_id` ASC) USING BTREE,
  INDEX `prescriptions::herb_id`(`herb_id` ASC) USING BTREE,
  CONSTRAINT `prescriptions::herb_id` FOREIGN KEY (`herb_id`) REFERENCES `herbs` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prescriptions::prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `prescription_infos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '处方' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of prescriptions
-- ----------------------------
INSERT INTO `prescriptions` VALUES (1, 1, 1, '3~9g', '煎服');
INSERT INTO `prescriptions` VALUES (2, 2, 2, '6~12g', '煎服');
INSERT INTO `prescriptions` VALUES (3, 3, 3, '3~10g', '煎服');

SET FOREIGN_KEY_CHECKS = 1;
