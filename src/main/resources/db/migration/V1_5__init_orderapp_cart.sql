CREATE TABLE CART
(
    CART_ID SERIAL NOT NULL,
    TOTAL_QUANTITY  INT NOT NULL,
    TOTAL_PRICE NUMERIC(5, 2) NOT NULL,
    CLIENT_ID INT NOT NULL,
    PRIMARY KEY(CART_ID),
    CONSTRAINT FK_CART_CLIENT_ID
        FOREIGN KEY (CLIENT_ID)
            REFERENCES CLIENT (CLIENT_ID)
);