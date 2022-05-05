# uestc-Course-Design





## 这个是我电子科技大学的课程设计，做的 手机留言板

~~电科大二学生的优良传统~~





### 这里直接复制我的报告的摘要

` 手机留言板是一个类似 推特 的应用，它具有用户注册，发布带图片的消息，回复消息和对消息增删改查的功能。
它的后台使用 Spring 作为框架，支持微服务分布式部署，并可以通过 Nacos 可视化监测分布式服务器部署情况。
它的前端使用了 Vue 的技术，实现了前后端分离，并且通过 HBuilder 封装技术打包成 Android 和 IOS 软件，实现了代码多端部署。
后端数据库采用了 Mysql，具有轻量级和易用性。`



## 快速开始（部署）

### 你需要一个阿里云云服务器，并且开通阿里云的 oss （阿里云打钱）



## 环境必须一致（自己百度怎么安装）

Ubuntu 20.04

MySQL 8

java 8

阿里云 oss 





## 开始部署

### MySQL 建表

直接执行 sql 文件的 建表.sql 即可，那个剩下的是往那四个表里面加数据。



## 部署后台

进入后端文件夹



先更改配置文件，在 service\service_chat\src\main\resources\application.properties

具体是 mysql 账号 和 阿里云的 oss 账号。

（阿里云的 oss 的 jar 包不能直接从 maven 下载，需要去阿里云的 oss 的官网下）



然后

` mvn clean package`

在 \service\service_chat\target 下会有个 service_chat-0.0.1-SNAPSHOT.jar ，在后台运行就可以了。



## 部署前端

可以用 nginx

但是我做的是手机，所以直接打包成 apk 在手机上运行



## 打包软件

使用 HBuilder 

先进入 前端 文件夹

修改配置，在 config 里面的 dev.env.js 和 prod.env.js

修改成自己的 IP 和端口（云服务器的）

然后

`npm install`

`npm build`

会有个 dist 文件夹，把里面的内容拖进 HBuilder 即可打包（网上搜 vue 打包成 apk）



开始愉快的魔改吧



## 人工智能

做了个爬虫，爬的九歌

可以写五言律诗