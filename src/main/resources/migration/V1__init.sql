CREATE TABLE building (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(32) NOT NULL,
    city VARCHAR(32) NOT NULL,
    street VARCHAR(364) NOT NULL,
    number VARCHAR(12) NOT NULL,
);

CREATE TABLE person (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(16) NOT NULL,
    occupation VARCHAR(32) NOT NULL,
    floor INTEGER NOT NULL,
    img_url varchar(1024),
    building_id INTEGER NOT NULL,
    FOREIGN KEY (building_id) REFERENCES building(id)
    ON DELETE CASCADE
);
