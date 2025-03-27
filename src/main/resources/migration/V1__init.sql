CREATE TABLE author (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(16) NOT NULL,
    address VARCHAR(256) NOT NULL,
    img_url varchar(1024),
);

CREATE TABLE patent (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(32) NOT NULL,
    description VARCHAR(512) NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
    ON DELETE CASCADE
);
