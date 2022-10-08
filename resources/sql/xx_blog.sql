/*
Navicat MySQL Data Transfer

Source Server         : MySQL80
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : xx_blog

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-10-01 14:30:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2034 DEFAULT CHARSET=utf8mb3 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', 'system', null, '1', 'M', '0', '0', '', 'system', '0', '2021-11-12 10:46:19', '0', null, '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', 'user', 'system/user/index', '1', 'C', '0', '0', 'system:user:list', 'user', '0', '2021-11-12 10:46:19', '1', '2022-07-31 15:47:58', '用户管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', 'role', 'system/role/index', '1', 'C', '0', '0', 'system:role:list', 'peoples', '0', '2021-11-12 10:46:19', '0', null, '角色管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '1', 'C', '0', '0', 'system:menu:list', 'tree-table', '0', '2021-11-12 10:46:19', '0', null, '菜单管理菜单', '0');
INSERT INTO `sys_menu` VALUES ('1001', '用户查询', '100', '1', '', '', '1', 'F', '0', '0', 'system:user:query', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1002', '用户新增', '100', '2', '', '', '1', 'F', '0', '0', 'system:user:add', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '100', '3', '', '', '1', 'F', '0', '0', 'system:user:edit', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '100', '4', '', '', '1', 'F', '0', '0', 'system:user:remove', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1005', '用户导出', '100', '5', '', '', '1', 'F', '0', '0', 'system:user:export', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1006', '用户导入', '100', '6', '', '', '1', 'F', '0', '0', 'system:user:import', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1007', '重置密码', '100', '7', '', '', '1', 'F', '0', '0', 'system:user:resetPwd', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1008', '角色查询', '101', '1', '', '', '1', 'F', '0', '0', 'system:role:query', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1009', '角色新增', '101', '2', '', '', '1', 'F', '0', '0', 'system:role:add', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1010', '角色修改', '101', '3', '', '', '1', 'F', '0', '0', 'system:role:edit', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1011', '角色删除', '101', '4', '', '', '1', 'F', '0', '0', 'system:role:remove', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1012', '角色导出', '101', '5', '', '', '1', 'F', '0', '0', 'system:role:export', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1013', '菜单查询', '102', '1', '', '', '1', 'F', '0', '0', 'system:menu:query', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1014', '菜单新增', '102', '2', '', '', '1', 'F', '0', '0', 'system:menu:add', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1015', '菜单修改', '102', '3', '', '', '1', 'F', '0', '0', 'system:menu:edit', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('1016', '菜单删除', '102', '4', '', '', '1', 'F', '0', '0', 'system:menu:remove', '#', '0', '2021-11-12 10:46:19', '0', null, '', '0');
INSERT INTO `sys_menu` VALUES ('2017', '内容管理', '0', '4', 'content', null, '1', 'M', '0', '0', null, 'table', null, '2022-01-08 02:44:38', '1', '2022-07-31 12:34:23', '', '0');
INSERT INTO `sys_menu` VALUES ('2018', '分类管理', '2017', '1', 'category', 'content/category/index', '1', 'C', '0', '0', 'content:category:list', 'example', null, '2022-01-08 02:51:45', null, '2022-01-08 02:51:45', '', '0');
INSERT INTO `sys_menu` VALUES ('2019', '文章管理', '2017', '0', 'article', 'content/article/index', '1', 'C', '0', '0', 'content:article:list', 'build', null, '2022-01-08 02:53:10', null, '2022-01-08 02:53:10', '', '0');
INSERT INTO `sys_menu` VALUES ('2021', '标签管理', '2017', '6', 'tag', 'content/tag/index', '1', 'C', '0', '0', 'content:tag:index', 'button', null, '2022-01-08 02:55:37', null, '2022-01-08 02:55:50', '', '0');
INSERT INTO `sys_menu` VALUES ('2022', '友链管理', '2017', '4', 'link', 'content/link/index', '1', 'C', '0', '0', 'content:link:list', '404', null, '2022-01-08 02:56:50', null, '2022-01-08 02:56:50', '', '0');
INSERT INTO `sys_menu` VALUES ('2023', '写博文', '0', '0', 'write', 'content/article/write/index', '1', 'C', '0', '0', 'content:article:writer', 'build', null, '2022-01-08 03:39:58', '1', '2022-07-31 22:07:05', '', '0');
INSERT INTO `sys_menu` VALUES ('2024', '友链新增', '2022', '0', '', null, '1', 'F', '0', '0', 'content:link:add', '#', null, '2022-01-16 07:59:17', null, '2022-01-16 07:59:17', '', '0');
INSERT INTO `sys_menu` VALUES ('2025', '友链修改', '2022', '1', '', null, '1', 'F', '0', '0', 'content:link:edit', '#', null, '2022-01-16 07:59:44', null, '2022-01-16 07:59:44', '', '0');
INSERT INTO `sys_menu` VALUES ('2026', '友链删除', '2022', '1', '', null, '1', 'F', '0', '0', 'content:link:remove', '#', null, '2022-01-16 08:00:05', null, '2022-01-16 08:00:05', '', '0');
INSERT INTO `sys_menu` VALUES ('2027', '友链查询', '2022', '2', '', null, '1', 'F', '0', '0', 'content:link:query', '#', null, '2022-01-16 08:04:09', null, '2022-01-16 08:04:09', '', '0');
INSERT INTO `sys_menu` VALUES ('2028', '导出分类', '2018', '1', '', null, '1', 'F', '0', '0', 'content:category:export', '#', null, '2022-01-21 07:06:59', null, '2022-01-21 07:06:59', '', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '1', '0', '0', '0', '2021-11-12 10:46:19', '0', null, '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '0', '0', '0', '2021-11-12 10:46:19', '0', '2022-01-01 22:32:58', '普通角色');
INSERT INTO `sys_role` VALUES ('11', '嘎嘎嘎', 'aggag', '5', '0', '0', null, '2022-01-06 14:07:40', null, '2022-01-07 03:48:48', '嘎嘎嘎');
INSERT INTO `sys_role` VALUES ('12', '友链审核员', 'link', '1', '0', '0', null, '2022-01-16 06:49:30', null, '2022-01-16 08:05:09', null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0', '0');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '2000');
INSERT INTO `sys_role_menu` VALUES ('3', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '3');
INSERT INTO `sys_role_menu` VALUES ('3', '4');
INSERT INTO `sys_role_menu` VALUES ('3', '100');
INSERT INTO `sys_role_menu` VALUES ('3', '101');
INSERT INTO `sys_role_menu` VALUES ('3', '103');
INSERT INTO `sys_role_menu` VALUES ('3', '104');
INSERT INTO `sys_role_menu` VALUES ('3', '105');
INSERT INTO `sys_role_menu` VALUES ('3', '106');
INSERT INTO `sys_role_menu` VALUES ('3', '107');
INSERT INTO `sys_role_menu` VALUES ('3', '108');
INSERT INTO `sys_role_menu` VALUES ('3', '109');
INSERT INTO `sys_role_menu` VALUES ('3', '110');
INSERT INTO `sys_role_menu` VALUES ('3', '111');
INSERT INTO `sys_role_menu` VALUES ('3', '112');
INSERT INTO `sys_role_menu` VALUES ('3', '113');
INSERT INTO `sys_role_menu` VALUES ('3', '114');
INSERT INTO `sys_role_menu` VALUES ('3', '115');
INSERT INTO `sys_role_menu` VALUES ('3', '116');
INSERT INTO `sys_role_menu` VALUES ('3', '500');
INSERT INTO `sys_role_menu` VALUES ('3', '501');
INSERT INTO `sys_role_menu` VALUES ('3', '1001');
INSERT INTO `sys_role_menu` VALUES ('3', '1002');
INSERT INTO `sys_role_menu` VALUES ('3', '1003');
INSERT INTO `sys_role_menu` VALUES ('3', '1004');
INSERT INTO `sys_role_menu` VALUES ('3', '1005');
INSERT INTO `sys_role_menu` VALUES ('3', '1006');
INSERT INTO `sys_role_menu` VALUES ('3', '1007');
INSERT INTO `sys_role_menu` VALUES ('3', '1008');
INSERT INTO `sys_role_menu` VALUES ('3', '1009');
INSERT INTO `sys_role_menu` VALUES ('3', '1010');
INSERT INTO `sys_role_menu` VALUES ('3', '1011');
INSERT INTO `sys_role_menu` VALUES ('3', '1012');
INSERT INTO `sys_role_menu` VALUES ('3', '1017');
INSERT INTO `sys_role_menu` VALUES ('3', '1018');
INSERT INTO `sys_role_menu` VALUES ('3', '1019');
INSERT INTO `sys_role_menu` VALUES ('3', '1020');
INSERT INTO `sys_role_menu` VALUES ('3', '1021');
INSERT INTO `sys_role_menu` VALUES ('3', '1022');
INSERT INTO `sys_role_menu` VALUES ('3', '1023');
INSERT INTO `sys_role_menu` VALUES ('3', '1024');
INSERT INTO `sys_role_menu` VALUES ('3', '1025');
INSERT INTO `sys_role_menu` VALUES ('3', '1026');
INSERT INTO `sys_role_menu` VALUES ('3', '1027');
INSERT INTO `sys_role_menu` VALUES ('3', '1028');
INSERT INTO `sys_role_menu` VALUES ('3', '1029');
INSERT INTO `sys_role_menu` VALUES ('3', '1030');
INSERT INTO `sys_role_menu` VALUES ('3', '1031');
INSERT INTO `sys_role_menu` VALUES ('3', '1032');
INSERT INTO `sys_role_menu` VALUES ('3', '1033');
INSERT INTO `sys_role_menu` VALUES ('3', '1034');
INSERT INTO `sys_role_menu` VALUES ('3', '1035');
INSERT INTO `sys_role_menu` VALUES ('3', '1036');
INSERT INTO `sys_role_menu` VALUES ('3', '1037');
INSERT INTO `sys_role_menu` VALUES ('3', '1038');
INSERT INTO `sys_role_menu` VALUES ('3', '1039');
INSERT INTO `sys_role_menu` VALUES ('3', '1040');
INSERT INTO `sys_role_menu` VALUES ('3', '1041');
INSERT INTO `sys_role_menu` VALUES ('3', '1042');
INSERT INTO `sys_role_menu` VALUES ('3', '1043');
INSERT INTO `sys_role_menu` VALUES ('3', '1044');
INSERT INTO `sys_role_menu` VALUES ('3', '1045');
INSERT INTO `sys_role_menu` VALUES ('3', '1046');
INSERT INTO `sys_role_menu` VALUES ('3', '1047');
INSERT INTO `sys_role_menu` VALUES ('3', '1048');
INSERT INTO `sys_role_menu` VALUES ('3', '1049');
INSERT INTO `sys_role_menu` VALUES ('3', '1050');
INSERT INTO `sys_role_menu` VALUES ('3', '1051');
INSERT INTO `sys_role_menu` VALUES ('3', '1052');
INSERT INTO `sys_role_menu` VALUES ('3', '1053');
INSERT INTO `sys_role_menu` VALUES ('3', '1054');
INSERT INTO `sys_role_menu` VALUES ('3', '1055');
INSERT INTO `sys_role_menu` VALUES ('3', '1056');
INSERT INTO `sys_role_menu` VALUES ('3', '1057');
INSERT INTO `sys_role_menu` VALUES ('3', '1058');
INSERT INTO `sys_role_menu` VALUES ('3', '1059');
INSERT INTO `sys_role_menu` VALUES ('3', '1060');
INSERT INTO `sys_role_menu` VALUES ('3', '2000');
INSERT INTO `sys_role_menu` VALUES ('11', '1');
INSERT INTO `sys_role_menu` VALUES ('11', '100');
INSERT INTO `sys_role_menu` VALUES ('11', '101');
INSERT INTO `sys_role_menu` VALUES ('11', '102');
INSERT INTO `sys_role_menu` VALUES ('11', '103');
INSERT INTO `sys_role_menu` VALUES ('11', '104');
INSERT INTO `sys_role_menu` VALUES ('11', '105');
INSERT INTO `sys_role_menu` VALUES ('11', '106');
INSERT INTO `sys_role_menu` VALUES ('11', '107');
INSERT INTO `sys_role_menu` VALUES ('11', '108');
INSERT INTO `sys_role_menu` VALUES ('11', '500');
INSERT INTO `sys_role_menu` VALUES ('11', '501');
INSERT INTO `sys_role_menu` VALUES ('11', '1001');
INSERT INTO `sys_role_menu` VALUES ('11', '1002');
INSERT INTO `sys_role_menu` VALUES ('11', '1003');
INSERT INTO `sys_role_menu` VALUES ('11', '1004');
INSERT INTO `sys_role_menu` VALUES ('11', '1005');
INSERT INTO `sys_role_menu` VALUES ('11', '1006');
INSERT INTO `sys_role_menu` VALUES ('11', '1007');
INSERT INTO `sys_role_menu` VALUES ('11', '1008');
INSERT INTO `sys_role_menu` VALUES ('11', '1009');
INSERT INTO `sys_role_menu` VALUES ('11', '1010');
INSERT INTO `sys_role_menu` VALUES ('11', '1011');
INSERT INTO `sys_role_menu` VALUES ('11', '1012');
INSERT INTO `sys_role_menu` VALUES ('11', '1013');
INSERT INTO `sys_role_menu` VALUES ('11', '1014');
INSERT INTO `sys_role_menu` VALUES ('11', '1015');
INSERT INTO `sys_role_menu` VALUES ('11', '1016');
INSERT INTO `sys_role_menu` VALUES ('11', '1017');
INSERT INTO `sys_role_menu` VALUES ('11', '1018');
INSERT INTO `sys_role_menu` VALUES ('11', '1019');
INSERT INTO `sys_role_menu` VALUES ('11', '1020');
INSERT INTO `sys_role_menu` VALUES ('11', '1021');
INSERT INTO `sys_role_menu` VALUES ('11', '1022');
INSERT INTO `sys_role_menu` VALUES ('11', '1023');
INSERT INTO `sys_role_menu` VALUES ('11', '1024');
INSERT INTO `sys_role_menu` VALUES ('11', '1025');
INSERT INTO `sys_role_menu` VALUES ('11', '1026');
INSERT INTO `sys_role_menu` VALUES ('11', '1027');
INSERT INTO `sys_role_menu` VALUES ('11', '1028');
INSERT INTO `sys_role_menu` VALUES ('11', '1029');
INSERT INTO `sys_role_menu` VALUES ('11', '1030');
INSERT INTO `sys_role_menu` VALUES ('11', '1031');
INSERT INTO `sys_role_menu` VALUES ('11', '1032');
INSERT INTO `sys_role_menu` VALUES ('11', '1033');
INSERT INTO `sys_role_menu` VALUES ('11', '1034');
INSERT INTO `sys_role_menu` VALUES ('11', '1035');
INSERT INTO `sys_role_menu` VALUES ('11', '1036');
INSERT INTO `sys_role_menu` VALUES ('11', '1037');
INSERT INTO `sys_role_menu` VALUES ('11', '1038');
INSERT INTO `sys_role_menu` VALUES ('11', '1039');
INSERT INTO `sys_role_menu` VALUES ('11', '1040');
INSERT INTO `sys_role_menu` VALUES ('11', '1041');
INSERT INTO `sys_role_menu` VALUES ('11', '1042');
INSERT INTO `sys_role_menu` VALUES ('11', '1043');
INSERT INTO `sys_role_menu` VALUES ('11', '1044');
INSERT INTO `sys_role_menu` VALUES ('11', '1045');
INSERT INTO `sys_role_menu` VALUES ('11', '2000');
INSERT INTO `sys_role_menu` VALUES ('11', '2003');
INSERT INTO `sys_role_menu` VALUES ('11', '2004');
INSERT INTO `sys_role_menu` VALUES ('11', '2005');
INSERT INTO `sys_role_menu` VALUES ('11', '2006');
INSERT INTO `sys_role_menu` VALUES ('11', '2007');
INSERT INTO `sys_role_menu` VALUES ('11', '2008');
INSERT INTO `sys_role_menu` VALUES ('11', '2009');
INSERT INTO `sys_role_menu` VALUES ('11', '2010');
INSERT INTO `sys_role_menu` VALUES ('11', '2011');
INSERT INTO `sys_role_menu` VALUES ('11', '2012');
INSERT INTO `sys_role_menu` VALUES ('11', '2013');
INSERT INTO `sys_role_menu` VALUES ('11', '2014');
INSERT INTO `sys_role_menu` VALUES ('12', '2017');
INSERT INTO `sys_role_menu` VALUES ('12', '2022');
INSERT INTO `sys_role_menu` VALUES ('12', '2024');
INSERT INTO `sys_role_menu` VALUES ('12', '2025');
INSERT INTO `sys_role_menu` VALUES ('12', '2026');
INSERT INTO `sys_role_menu` VALUES ('12', '2027');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('5', '2');
INSERT INTO `sys_user_role` VALUES ('6', '12');

-- ----------------------------
-- Table structure for `xx_article`
-- ----------------------------
DROP TABLE IF EXISTS `xx_article`;
CREATE TABLE `xx_article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(1024) DEFAULT NULL COMMENT '文章摘要',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) DEFAULT NULL COMMENT '缩略图',
  `is_top` char(1) DEFAULT '0' COMMENT '是否置顶（0否，1是）',
  `status` char(1) DEFAULT '1' COMMENT '状态（0已发布，1草稿）',
  `view_count` bigint DEFAULT '0' COMMENT '访问量',
  `is_comment` char(1) DEFAULT '1' COMMENT '是否允许评论 1是，0否',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';

-- ----------------------------
-- Records of xx_article
-- ----------------------------
INSERT INTO `xx_article` VALUES ('1', 'SpringSecurity从入门到精通', '## 课程介绍\n![image20211219121555979.png](https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/e7131718e9e64faeaf3fe16404186eb4.png)\n\n## 0. 简介1\n\n​	**Spring Security** 是 Spring 家族中的一个安全管理框架。相比与另外一个安全框架**Shiro**，它提供了更丰富的功能，社区资源也比Shiro丰富。\n\n​	一般来说中大型的项目都是使用**SpringSecurity** 来做安全框架。小项目有Shiro的比较多，因为相比与SpringSecurity，Shiro的上手更加的简单。\n\n​	 一般Web应用的需要进行**认证**和**授权**。\n\n​		**认证：验证当前访问系统的是不是本系统的用户，并且要确认具体是哪个用户**\n\n​		**授权：经过认证后判断当前用户是否有权限进行某个操作**\n\n​	而认证和授权也是SpringSecurity作为安全框架的核心功能。\n\n\n\n## 1. 快速入门\n\n### 1.1 准备工作\n\n​	我们先要搭建一个简单的SpringBoot工程\n\n① 设置父工程 添加依赖\n\n~~~~\n    <parent>\n        <groupId>org.springframework.boot</groupId>\n        <artifactId>spring-boot-starter-parent</artifactId>\n        <version>2.5.0</version>\n    </parent>\n    <dependencies>\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-web</artifactId>\n        </dependency>\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <optional>true</optional>\n        </dependency>\n    </dependencies>\n~~~~\n\n② 创建启动类\n\n~~~~\n@SpringBootApplication\npublic class SecurityApplication {\n\n    public static void main(String[] args) {\n        SpringApplication.run(SecurityApplication.class,args);\n    }\n}\n\n~~~~\n\n③ 创建Controller\n\n~~~~java\n\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\npublic class HelloController {\n\n    @RequestMapping(\"/hello\")\n    public String hello(){\n        return \"hello\";\n    }\n}\n\n~~~~\n\n\n\n### 1.2 引入SpringSecurity\n\n​	在SpringBoot项目中使用SpringSecurity我们只需要引入依赖即可实现入门案例。\n\n~~~~xml\n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-security</artifactId>\n        </dependency>\n~~~~\n\n​	引入依赖后我们在尝试去访问之前的接口就会自动跳转到一个SpringSecurity的默认登陆页面，默认用户名是user,密码会输出在控制台。\n\n​	必须登陆之后才能对接口进行访问。\n\n\n\n## 2. 认证\n\n### 2.1 登陆校验流程\n![image20211215094003288.png](https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/414a87eeed344828b5b00ffa80178958.png)', 'SpringSecurity框架教程-Spring Security+JWT实现项目级前端分离认证授权', '1', 'https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/31/948597e164614902ab1662ba8452e106.png', '1', '0', '105', '0', null, '2022-01-23 23:20:11', null, null, '0');
INSERT INTO `xx_article` VALUES ('2', 'weq', 'adadaeqe', 'adad', '2', 'https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/fd2e9460c58a4af3bbeae5d9ed581688.png', '1', '0', '22', '0', null, '2022-01-21 14:58:30', null, null, '1');
INSERT INTO `xx_article` VALUES ('3', 'dad', 'asdasda', 'sadad', '1', 'https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/737a0ed0b8ea430d8700a12e76aa1cd1.png', '1', '0', '33', '0', null, '2022-01-18 14:58:34', null, null, '1');
INSERT INTO `xx_article` VALUES ('5', 'sdad', '![Snipaste_20220115_165812.png](https://xx-blog-oss.oss-cn-beijing.aliyuncs.com/2022/01/15/1d9d283f5d874b468078b183e4b98b71.png)\r\n\r\n## sda \r\n\r\n222\r\n### sdasd newnewnew', null, '2', '', '1', '0', '44', '0', null, '2022-01-17 14:58:37', null, null, '0');

-- ----------------------------
-- Table structure for `xx_article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `xx_article_tag`;
CREATE TABLE `xx_article_tag` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签关联表';

-- ----------------------------
-- Records of xx_article_tag
-- ----------------------------
INSERT INTO `xx_article_tag` VALUES ('1', '4');
INSERT INTO `xx_article_tag` VALUES ('2', '1');
INSERT INTO `xx_article_tag` VALUES ('2', '4');
INSERT INTO `xx_article_tag` VALUES ('3', '4');
INSERT INTO `xx_article_tag` VALUES ('3', '5');

-- ----------------------------
-- Table structure for `xx_category`
-- ----------------------------
DROP TABLE IF EXISTS `xx_category`;
CREATE TABLE `xx_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '分类名',
  `pid` bigint DEFAULT '-1' COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';

-- ----------------------------
-- Records of xx_category
-- ----------------------------
INSERT INTO `xx_category` VALUES ('1', 'java', '-1', 'wsd', '0', null, null, null, null, '0');
INSERT INTO `xx_category` VALUES ('2', 'PHP', '-1', 'wsd', '0', null, null, null, null, '0');

-- ----------------------------
-- Table structure for `xx_comment`
-- ----------------------------
DROP TABLE IF EXISTS `xx_comment`;
CREATE TABLE `xx_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT '0' COMMENT '评论类型（0代表文章评论，1代表友链评论）',
  `article_id` bigint DEFAULT NULL COMMENT '文章id',
  `root_id` bigint DEFAULT '-1' COMMENT '根评论id',
  `content` varchar(512) DEFAULT NULL COMMENT '评论内容',
  `to_comment_user_id` bigint DEFAULT '-1' COMMENT '所回复的目标评论的userid',
  `to_comment_id` bigint DEFAULT '-1' COMMENT '回复目标评论id',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';

-- ----------------------------
-- Records of xx_comment
-- ----------------------------
INSERT INTO `xx_comment` VALUES ('1', '0', '1', '-1', 'asS', '-1', '-1', '1', '2022-01-29 07:59:22', '1', '2022-01-29 07:59:22', '0');
INSERT INTO `xx_comment` VALUES ('2', '0', '1', '-1', '[哈哈]SDAS', '-1', '-1', '1', '2022-01-29 08:01:24', '1', '2022-01-29 08:01:24', '0');
INSERT INTO `xx_comment` VALUES ('3', '0', '1', '-1', '是大多数', '-1', '-1', '1', '2022-01-29 16:07:24', '1', '2022-01-29 16:07:24', '0');
INSERT INTO `xx_comment` VALUES ('4', '0', '1', '-1', '撒大声地', '-1', '-1', '1', '2022-01-29 16:12:09', '1', '2022-01-29 16:12:09', '0');
INSERT INTO `xx_comment` VALUES ('5', '0', '1', '-1', '你再说什么', '-1', '-1', '1', '2022-01-29 18:19:56', '1', '2022-01-29 18:19:56', '0');
INSERT INTO `xx_comment` VALUES ('6', '0', '1', '-1', 'hffd', '-1', '-1', '1', '2022-01-29 22:13:52', '1', '2022-01-29 22:13:52', '0');
INSERT INTO `xx_comment` VALUES ('9', '0', '1', '2', '你说什么', '1', '2', '1', '2022-01-29 22:18:40', '1', '2022-01-29 22:18:40', '0');
INSERT INTO `xx_comment` VALUES ('10', '0', '1', '2', '哈哈哈哈[哈哈]', '1', '9', '1', '2022-01-29 22:29:15', '1', '2022-01-29 22:29:15', '0');
INSERT INTO `xx_comment` VALUES ('11', '0', '1', '2', 'we全文', '1', '10', '3', '2022-01-29 22:29:55', '1', '2022-01-29 22:29:55', '0');
INSERT INTO `xx_comment` VALUES ('12', '0', '1', '-1', '王企鹅', '-1', '-1', '1', '2022-01-29 22:30:20', '1', '2022-01-29 22:30:20', '0');
INSERT INTO `xx_comment` VALUES ('13', '0', '1', '-1', '什么阿是', '-1', '-1', '1', '2022-01-29 22:30:56', '1', '2022-01-29 22:30:56', '0');
INSERT INTO `xx_comment` VALUES ('14', '0', '1', '-1', '新平顶山', '-1', '-1', '1', '2022-01-29 22:32:51', '1', '2022-01-29 22:32:51', '0');
INSERT INTO `xx_comment` VALUES ('15', '0', '1', '-1', '2222', '-1', '-1', '1', '2022-01-29 22:34:38', '1', '2022-01-29 22:34:38', '0');
INSERT INTO `xx_comment` VALUES ('16', '0', '1', '2', '3333', '1', '11', '1', '2022-01-29 22:34:47', '1', '2022-01-29 22:34:47', '0');
INSERT INTO `xx_comment` VALUES ('17', '0', '1', '2', '回复weqedadsd', '3', '11', '1', '2022-01-29 22:38:00', '1', '2022-01-29 22:38:00', '0');
INSERT INTO `xx_comment` VALUES ('18', '0', '1', '-1', 'sdasd', '-1', '-1', '1', '2022-01-29 23:18:19', '1', '2022-01-29 23:18:19', '0');
INSERT INTO `xx_comment` VALUES ('19', '0', '1', '-1', '111', '-1', '-1', '1', '2022-01-29 23:22:23', '1', '2022-01-29 23:22:23', '0');
INSERT INTO `xx_comment` VALUES ('20', '0', '1', '1', '你说啥？', '1', '1', '1', '2022-01-30 10:06:21', '1', '2022-01-30 10:06:21', '0');
INSERT INTO `xx_comment` VALUES ('21', '0', '1', '-1', '友链添加个呗', '-1', '-1', '1', '2022-01-30 10:06:50', '1', '2022-01-30 10:06:50', '0');
INSERT INTO `xx_comment` VALUES ('22', '1', '1', '-1', '友链评论2', '-1', '-1', '1', '2022-01-30 10:08:28', '1', '2022-01-30 10:08:28', '0');
INSERT INTO `xx_comment` VALUES ('23', '1', '1', '22', '回复友链评论3', '1', '22', '1', '2022-01-30 10:08:50', '1', '2022-01-30 10:08:50', '0');
INSERT INTO `xx_comment` VALUES ('24', '1', '1', '-1', '友链评论4444', '-1', '-1', '1', '2022-01-30 10:09:03', '1', '2022-01-30 10:09:03', '0');
INSERT INTO `xx_comment` VALUES ('25', '1', '1', '22', '收到的', '1', '22', '1', '2022-01-30 10:13:28', '1', '2022-01-30 10:13:28', '0');
INSERT INTO `xx_comment` VALUES ('26', '0', '1', '-1', 'sda', '-1', '-1', '1', '2022-01-30 10:39:05', '1', '2022-01-30 10:39:05', '0');
INSERT INTO `xx_comment` VALUES ('27', '0', '1', '1', '说你咋地', '1', '20', '14787164048662', '2022-01-30 17:19:30', '14787164048662', '2022-01-30 17:19:30', '0');
INSERT INTO `xx_comment` VALUES ('28', '0', '1', '1', 'sdad', '1', '1', '14787164048662', '2022-01-31 11:11:20', '14787164048662', '2022-01-31 11:11:20', '0');
INSERT INTO `xx_comment` VALUES ('29', '0', '1', '-1', '你说是的ad', '-1', '-1', '14787164048662', '2022-01-31 14:10:11', '14787164048662', '2022-01-31 14:10:11', '0');
INSERT INTO `xx_comment` VALUES ('30', '0', '1', '1', '撒大声地', '1', '1', '14787164048662', '2022-01-31 20:19:18', '14787164048662', '2022-01-31 20:19:18', '0');

-- ----------------------------
-- Table structure for `xx_link`
-- ----------------------------
DROP TABLE IF EXISTS `xx_link`;
CREATE TABLE `xx_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `logo` varchar(256) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL COMMENT '网站地址',
  `status` char(1) DEFAULT '2' COMMENT '审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';

-- ----------------------------
-- Records of xx_link
-- ----------------------------
INSERT INTO `xx_link` VALUES ('1', 'sda', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'sda', 'https://www.baidu.com', '1', null, '2022-01-13 08:25:47', null, '2022-01-13 08:36:14', '0');
INSERT INTO `xx_link` VALUES ('2', 'sda', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'dada', 'https://www.qq.com', '1', null, '2022-01-13 09:06:10', null, '2022-01-13 09:07:09', '0');
INSERT INTO `xx_link` VALUES ('3', 'sa', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F05%2F10%2F146286696706220328.PNG&refer=http%3A%2F%2Fn1.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646205529&t=f942665181eb9b0685db7a6f59d59975', 'da', 'https://www.taobao.com', '1', null, '2022-01-13 09:23:01', null, '2022-01-13 09:23:01', '0');

-- ----------------------------
-- Table structure for `xx_tag`
-- ----------------------------
DROP TABLE IF EXISTS `xx_tag`;
CREATE TABLE `xx_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签';

-- ----------------------------
-- Records of xx_tag
-- ----------------------------
INSERT INTO `xx_tag` VALUES ('1', 'Mybatis', null, null, null, '2022-01-11 09:20:50', '0', 'weqwe');
INSERT INTO `xx_tag` VALUES ('2', 'asdas', null, '2022-01-11 09:20:55', null, '2022-01-11 09:20:55', '1', 'weqw');
INSERT INTO `xx_tag` VALUES ('3', 'weqw', null, '2022-01-11 09:21:07', null, '2022-01-11 09:21:07', '1', 'qweqwe');
INSERT INTO `xx_tag` VALUES ('4', 'Java', null, '2022-01-13 15:22:43', null, '2022-01-13 15:22:43', '0', 'sdad');
INSERT INTO `xx_tag` VALUES ('5', 'WAD', null, '2022-01-13 15:22:47', null, '2022-01-13 15:22:47', '0', 'ASDAD');
