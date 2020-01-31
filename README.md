



# APACHE SHARDING-SPHERE MANUAL
- https://shardingsphere.apache.org/document/current/en/faq/
# SNOWFLAKE?
- https://charsyam.wordpress.com/2012/12/26/%EC%9E%85-%EA%B0%9C%EB%B0%9C-global-unique-object-id-%EC%83%9D%EC%84%B1-%EB%B0%A9%EB%B2%95%EC%97%90-%EB%8C%80%ED%95%9C-%EC%A0%95%EB%A6%AC/


[SHARDING-JDBC 예제](https://juejin.im/post/5d9fe175f265da5bba417a28)

## 예제 SQL
```sql
create schema ds0; 
create schema ds1;

create table ds0.t_order
(
	id bigint not null
		primary key,
	name varchar(255) null comment 'Name',
	type varchar(255) null comment 'type',
	gmt_create timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Creation time'
);

create table ds1.t_order
(
	id bigint not null
		primary key,
	name varchar(255) null comment 'Name',
	type varchar(255) null comment 'type',
	gmt_create timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Creation time'
);

-- https://dev.mysql.com/doc/employee/en/employees-installation.html
```