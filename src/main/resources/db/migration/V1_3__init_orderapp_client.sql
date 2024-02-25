CREATE TABLE CLIENT
(
    CLIENT_ID   INT      NOT NULL,
    NAME        VARCHAR(64) NOT NULL,
    SURNAME     VARCHAR(64) NOT NULL,
    PHONE       VARCHAR(20) NOT NULL,
    EMAIL       VARCHAR(255) NOT NULL,
    PRIMARY KEY (CLIENT_ID),
    UNIQUE (EMAIL)

);