CREATE TABLE MEAL
(
    MEAL_ID             SERIAL       NOT NULL,
    CATEGORY            VARCHAR(20)  NOT NULL,
    MENU_ID             INT          NOT NULL,
    DESCRIPTION         VARCHAR(64)  NOT NULL,
    PRICE               NUMERIC(3,2) NOT NULL,
    PRIMARY KEY (MEAL_ID),
    CONSTRAINT FK_MEAL_MENU_ID
            FOREIGN KEY (MENU_ID)
                REFERENCES MENU (MENU_ID)
);
