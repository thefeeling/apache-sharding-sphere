CREATE DATABASE IF NOT EXISTS `ecommerce_development`;
CREATE DATABASE IF NOT EXISTS `ecommerce01_development`;
CREATE DATABASE IF NOT EXISTS `ecommerce02_development`;
CREATE DATABASE IF NOT EXISTS `ecommerce03_development`;
CREATE DATABASE IF NOT EXISTS `ecommerce04_development`;

CREATE USER 'ecommerce'@'%' IDENTIFIED BY 'ecommerce#local';
GRANT ALL PRIVILEGES ON *.* TO 'ecommerce'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE USER 'ecommerce01'@'%' IDENTIFIED BY 'ecommerce01#local';
GRANT ALL PRIVILEGES ON ecommerce01_development.* TO 'ecommerce01'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE USER 'ecommerce02'@'%' IDENTIFIED BY 'ecommerce02#local';
GRANT ALL PRIVILEGES ON ecommerce02_development.* TO 'ecommerce02'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE USER 'ecommerce03'@'%' IDENTIFIED BY 'ecommerce03#local';
GRANT ALL PRIVILEGES ON ecommerce03_development.* TO 'ecommerce03'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE USER 'ecommerce04'@'%' IDENTIFIED BY 'ecommerce04#local';
GRANT ALL PRIVILEGES ON ecommerce04_development.* TO 'ecommerce04'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
