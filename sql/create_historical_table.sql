
CREATE TABLE Historical (
    ID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    share_count INT DEFAULT 0,
    date_bought DATETIME NOT NULL,
    date_sold DATETIME NOT NULL,
    PRIMARY KEY (ID)
);