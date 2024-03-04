CREATE TABLE order_item (
    order_item_id SERIAL NOT NULL,
    order_id INT NOT NULL,
    meal_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (order_item_id),
    CONSTRAINT FK_ORDER_ITEM_ORDER
                    FOREIGN KEY (ORDER_ID)
                        REFERENCES NEW_ORDER (ORDER_ID),
    CONSTRAINT FK_ORDER_ITEM_MEAL
                    FOREIGN KEY (MEAL_ID)
                        REFERENCES MEAL (MEAL_ID)
);