CREATE SCHEMA minazg_db;

CREATE USER 'minazg'@'localhost' IDENTIFIED BY 'minazg';
GRANT ALL PRIVILEGES ON *.* TO 'minazg'@'localhost';
FLUSH PRIVILEGES;