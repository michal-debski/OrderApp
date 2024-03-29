CREATE TABLE STREET
(
 STREET_ID      SERIAL  NOT NULL,
 NAME           VARCHAR(200) NOT NULL,
 RESTAURANT_ID  INT NOT NULL,
 PRIMARY KEY(STREET_ID),
 CONSTRAINT FK_STREET_RESTAURANT
          FOREIGN KEY (RESTAURANT_ID)
             REFERENCES RESTAURANT (RESTAURANT_ID)
)