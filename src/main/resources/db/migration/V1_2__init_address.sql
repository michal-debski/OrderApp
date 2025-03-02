create TABLE address
(
    ADDRESS_ID          SERIAL      NOT NULL,
    COUNTRY             VARCHAR(32) NOT NULL,
    CITY                VARCHAR(32) NOT NULL,
    STREET              VARCHAR(64) NOT NULL,
    NUMBER     VARCHAR(32) NOT NULL,
    PRIMARY KEY (ADDRESS_ID)
);

