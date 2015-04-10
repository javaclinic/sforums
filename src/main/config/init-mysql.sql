CREATE TABLE IF NOT EXISTS category (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    description TEXT,
    PRIMARY KEY  (id),
    UNIQUE KEY name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    organization VARCHAR(64),
    title VARCHAR(64),
    email VARCHAR(64) NOT NULL,
    password VARCHAR(32),
    created DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE user CHANGE COLUMN password passwordDigest VARCHAR(32);
UPDATE user SET passwordDigest=MD5(passwordDigest) WHERE LENGTH(passwordDigest) != 32;

ALTER TABLE user ADD COLUMN version INT(11) NOT NULL DEFAULT 0;
ALTER TABLE category ADD COLUMN version INT(11) NOT NULL DEFAULT 0;

ALTER TABLE user ADD COLUMN admin BIT(1) NOT NULL DEFAULT b'0';
ALTER TABLE user ADD COLUMN enabled BIT(1) NOT NULL DEFAULT b'1';
