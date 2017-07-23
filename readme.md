### 各服务说明
|工程名|服务名|端口号|备注|
|:-------|:--------|-------:|---|
|services-register|无|5500|注册中心
|gateway|ag|5000|注册中心
|user|user|5600|用户服务
|web|web|80|前端工程,或者可以使用nginx


### url
##### eureka
    http://localhost:5500/
##### web
    http://localhost:5000
##### user
    http://localhost:5600


### ddl
create table user
(
	id bigint auto_increment
		primary key,
	address varchar(255) null,
	age int null,
	login_name varchar(255) null,
	name varchar(255) null,
	password varchar(255) null,
	sex int null
)
;

