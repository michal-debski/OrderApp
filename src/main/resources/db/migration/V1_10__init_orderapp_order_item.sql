CREATE TABLE ORDER_ITEM
(
    ORDER_ITEM_ID       INT       NOT NULL,
    QUANTITY            INT          NOT NULL,
    PRICE               NUMERIC(5,2) NOT NULL,
    MEAL_ID             INT  NOT NULL,
    CART_ID             INT          NOT NULL,
    PRIMARY KEY (ORDER_ITEM_ID),
    CONSTRAINT FK_ORDER_ITEM_MEAL
            FOREIGN KEY (MEAL_ID)
                REFERENCES MEAL (MEAL_ID),
    CONSTRAINT FK_ORDER_ITEM_CART
            FOREIGN KEY (CART_ID)
                REFERENCES CART (CART_ID)
);