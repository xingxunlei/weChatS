/*==============================================================*/
/* TABLE: WECHAT_MENU                                         */
/*==============================================================*/
DROP TABLE IF EXISTS WECHAT_MENU;
CREATE TABLE WECHAT_MENU
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   SN                   VARCHAR(100) COMMENT '权限名称',
   MENU_NAME            VARCHAR(200) COMMENT '菜单名称',
   PRIORITY             INT(11) DEFAULT 1 COMMENT '菜单顺序',
   URL                  CHAR(200) DEFAULT '#' COMMENT '是否有效（1=有效，0=无效）',
   PARENT_ID            INT(11) DEFAULT 0 COMMENT '菜单父ID',
   IS_MENU              CHAR(1) DEFAULT '1' COMMENT '是否是菜单（1=菜单，0=按钮）',
   DESCRIPTION          VARCHAR(500) COMMENT '描述',
   CREATE_BY            INT COMMENT '创建者ID',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   MODIFY_BY            INT COMMENT '修改者ID',
   MODIFY_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='菜单栏目表';

/*==============================================================*/
/* TABLE: WECHAT_ROLE                                         */
/*==============================================================*/
DROP TABLE IF EXISTS WECHAT_ROLE;
CREATE TABLE WECHAT_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   ROLE_NAME            VARCHAR(100) COMMENT '角色名称',
   SYSTEM_CODE          CHAR(1) DEFAULT '1' COMMENT '角色所属系统代码(0=前台系统，1=后台系统)',
   CREATE_BY            INT COMMENT '创建者ID',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   MODIFY_BY            INT COMMENT '修改者ID',
   MODIFY_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='角色表';

/*==============================================================*/
/* TABLE: WECHAT_ROLE_PERMISSION                              */
/*==============================================================*/
DROP TABLE IF EXISTS WECHAT_ROLE_PERMISSION;
CREATE TABLE WECHAT_ROLE_PERMISSION
(
   ROLE_ID              INT NOT NULL COMMENT '角色ID',
   PERMISSION           VARCHAR(100) NOT NULL COMMENT '权限ID',
   CREATE_BY            INT COMMENT '创建者ID',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   MODIFY_BY            INT COMMENT '修改者ID',
   MODIFY_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (ROLE_ID, PERMISSION)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='角色权限表';

/*==============================================================*/
/* TABLE: WECHAT_USER                                         */
/*==============================================================*/
DROP TABLE IF EXISTS WECHAT_USER;
CREATE TABLE WECHAT_USER
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   LOGIN_NAME           VARCHAR(50) COMMENT '登录名称',
   USER_NAME            VARCHAR(100) COMMENT '用户姓名',
   NICK_NAME            VARCHAR(100) COMMENT '用户昵称',
   MOBILE_PHONE         VARCHAR(50) COMMENT '手机号码',
   EMAIL                VARCHAR(100) COMMENT 'EMAIL',
   PASSWORD             VARCHAR(100) COMMENT '登录密码',
   SALT                 VARCHAR(64) COMMENT '加密因子',
   LOGIN_TIME           DATETIME COMMENT '最近登录时间',
   USER_IMG             VARCHAR(100) COMMENT '用户图片',
   USER_SEX             CHAR(1) DEFAULT '2' COMMENT '用户性别(1=男，0=女，2=保密)',
   USER_TYPE            CHAR(1) DEFAULT '1' COMMENT '用户类型（0=前台会员用户，1=后台管理用户）',
   IS_VALID             CHAR(1) DEFAULT '1' COMMENT '是否有效（1=有效，0=无效）',
   BOTH_LOGIN           CHAR(1) DEFAULT '0' COMMENT '是否允许同时登录（1=允许，0=不允许）',
   CREATE_BY            INT COMMENT '创建者ID',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   MODIFY_BY            INT COMMENT '修改者ID',
   MODIFY_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户表';

/*==============================================================*/
/* TABLE: WECHAT_USER_ROLE                                    */
/*==============================================================*/
DROP TABLE IF EXISTS WECHAT_USER_ROLE;
CREATE TABLE WECHAT_USER_ROLE
(
   ID                   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
   ROLE_ID              INT COMMENT '权限ID',
   USER_ID              INT COMMENT '用户ID',
   CREATE_BY            INT COMMENT '创建者ID',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   MODIFY_BY            INT COMMENT '修改者ID',
   MODIFY_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (ID)
)ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户角色表';
