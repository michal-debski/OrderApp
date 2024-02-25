insert into client (CLIENT_ID, name, surname, phone, email)
values
(1,'John', 'Doe', '123-456-789', 'john.doe@example.com'),
(2,'Alice', 'Smith', '987-654-321', 'alice.smith@example.com'),
(3,'Bob', 'Johnson', '555-555-555', 'bob.johnson@example.com');

insert into restaurant_owner(RESTAURANT_OWNER_ID, name, surname, email)
values
(1,'Stefan', 'Stefanowicz','stefan@miastowa.com'),
(2,'Agata', 'Agrafka', 'agata@victi.com'),
(3,'Tomasz', 'Tomaszewski', 'tomasz@tomani.com');


INSERT INTO RESTAURANT(RESTAURANT_ID,NAME, RESTAURANT_OWNER_ID)
VALUES
(1,'Miastowa',1),
(2,'Victi',2),
(3,'Tomani',3);

INSERT INTO STREET(STREET_ID, NAME, RESTAURANT_ID)
VALUES
(1,'Przymorska', 1),
(2,'Morska', 1),
(3,'Oceanowa', 1),
(4,'Jeziorna', 2),
(5,'Tomaszowska', 2),
(6,'Piotrkowska', 2),
(7,'Warszawska', 3),
(8,'Kotowa', 3),
(9,'Psia', 3),
(10,'Dzwon', 3);

INSERT INTO CATEGORY(CATEGORY_ID, NAME)
VALUES
(1,'DESSERTS'), (2, 'MAIN DISH'), (3, 'SOUP'), (4, 'DRINK');

insert into meal(MEAL_ID,name, category_ID, description, price, restaurant_id)
values
(1,'Apple dumplings', 1, 'Delicious apple dumplings', 16.00, 1),
(2,'Strawberry dumplings', 1, 'Strawberry strawberry dumplings', 15.00, 1),
(3,'Orange dumplings', 1, 'Delicious orange dumplings', 20.00, 2),
(4,'Pancakes with chocolate', 1, 'Delicious pancakes with chocolate', 27.00, 2),
(5,'Pancakes with powdered sugar', 1, 'Delicious pancakes with powdered sugar', 15.00, 3),
(6,'Pancakes with strawberries', 1, 'Delicious pancakes with strawberries', 16.00, 3),
(7,'Pasta with cheese and spinach', 2, 'Delicious pasta with cheese and spinach', 30.00, 1),
(8,'Chicken quarter with roasted potatoes', 2, 'Delicious Chicken quarter with roasted potatoes', 35.00, 1),
(9,'Pasta with tuna and tomatoes', 2, 'Delicious pasta with tuna and tomatoes',27.00, 1),
(10,'Chicken leg with potatoes', 2, 'Delicious chicken leg with potatoes', 35.00, 1),
(11,'Pasta with tomatoes and basil', 2, 'Delicious pasta with tomatoes and basil', 26.00, 2),
(12,'Pasta with tomatoes and cheese', 2, 'Delicious Pasta with tomatoes and cheese', 28.00, 3),
(13,'Pasta with broccoli sauce', 2, 'Delicious pasta with broccoli sauce', 29.00, 2),
(14,'Chicken with potatoes', 2, 'Delicious chicken with potatoes', 35.00, 3),
(15,'Chicken with french fries', 2, 'Delicious chicken with fries', 35.00, 2),
(16,'Pork chop with cabbage and mushrooms', 2, 'Delicious pork chop with cabbage and mushrooms', 35.00, 3),
(17,'Tomato soup with noodles', 3, 'Delicious tomato soup with noodles', 15.00, 1),
(18,'Broth with noodles', 3, 'Delicious broth with noodles', 15.00, 1),
(19,'Krupnik', 3, 'Delicious krupnik', 15.00, 2),
(20,'Vegetable soup', 3, 'Delicious vegetable soup', 15.00, 2),
(21,'Ramen soup', 3, 'Delicious ramen soup', 15.00, 3),
(22,'Coca-cola', 4, 'Delicious coca-cola', 7.00, 1),
(23,'Apple juice', 4, 'Delicious apple juice', 7.00, 1),
(24,'Fanta', 4, 'Delicious fanta', 7.00, 2),
(25,'Orange juice', 4, 'Delicious orange juice', 7.00, 3),
(26,'7up', 4, 'Delicious 7up', 7.00, 3),
(27,'Ham and cheese sandwiches', 4, 'Delicious ham and cheese sandwiches', 10.00, 1),
(28,'Panini with cheese', 4, 'Delicious panini with cheese', 10.00, 2),
(29,'Salmon sandwiches', 4, 'Delicious salmon sandwiches', 9.00, 3),
(30,'Sandwiches with egg paste', 4, 'Delicious sandwiches with egg paste', 9.00, 3);
