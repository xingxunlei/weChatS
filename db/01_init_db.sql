DROP DATABASE wechat;
CREATE DATABASE wechat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON wechat.* TO wechatapp@'%' IDENTIFIED BY 'wechatpwd';