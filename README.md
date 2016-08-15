# weChatS
微信开发框架

目录介绍：  
db：数据库相关sql文件目录  
generator：mybatis-generator工具config.xml目录  
src：源码目录  
target：maven自动生成目录

maven 集成了 mybatis自动生成代码的插件。  
运行命令：mvn mybatis-generator:generate  
可自动生成model，mapping.xml以及dao代码，根据需要可自行修改生成class的文件名称

maven 打包命令  
mvn -Pxxx package  
xxx：具体环境配置文件的目录名称，在pom.xml文件中有定义。  
（test，测试环境 。product，生产环境。dev，开发环境，此环境打包直接运行mvn package 即可。）
