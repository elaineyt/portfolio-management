CREATE DATABASE group_30;

CREATE USER 'group30'@'%' IDENTIFIED WITH mysql_native_password BY '6HUTzqpAv#SL2kafG#!p';

CREATE TABLE Portfolio (
    ID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    share_count INT DEFAULT 0,
    date_bought DATETIME NOT NULL,
    date_sold DATETIME NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Users (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);

GRANT ALL PRIVILEGES ON group_30.* TO 'group30'@'%';

FLUSH PRIVILEGES;
