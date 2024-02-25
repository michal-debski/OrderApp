CREATE TABLE ORDER_DETAIL
(
  order_detail_id INT        NOT NULL,
  total_quantity  INT           NOT NULL,
  total_price      NUMERIC(4,2)  NOT NULL,
  meal_id         INT           NOT NULL,
  PRIMARY KEY(ORDER_DETAIL_ID),
  CONSTRAINT FK_ORDER_DETAIL_MEAL
         FOREIGN KEY (MEAL_ID)
            REFERENCES MEAL (MEAL_ID)
);
