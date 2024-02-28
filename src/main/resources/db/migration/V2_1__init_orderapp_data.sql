

insert into client (name, surname, phone, email)
values
('John', 'Doe', '123-456-789', 'john.doe@example.com'),
('Alice', 'Smith', '987-654-321', 'alice.smith@example.com'),
('Bob', 'Johnson', '555-555-555', 'bob.johnson@example.com');


insert into restaurant_owner( name, surname, email)
values
('Stefan', 'Stefanowicz','stefan@miastowa.com'),
('Agata', 'Agrafka', 'agata@victi.com'),
('Tomasz', 'Tomaszewski', 'tomasz@tomani.com');


INSERT INTO RESTAURANT(NAME, RESTAURANT_OWNER_ID)
VALUES
('Miastowa',1),
('Victi',2),
('Tomani',3);

INSERT INTO STREET(NAME, RESTAURANT_ID)
VALUES
('Przymorska', 1),
('Morska', 1),
('Oceanowa', 1),
('Jeziorna', 2),
('Tomaszowska', 2),
('Piotrkowska', 2),
('Warszawska', 3),
('Kotowa', 3),
('Psia', 3),
('Dzwon', 3);

INSERT INTO CATEGORY( NAME)
VALUES
('DESSERTS'), ( 'MAIN DISH'), ( 'SOUP'), ( 'DRINK');

insert into meal(name, category_ID, description, price, restaurant_id)
values
('Apple dumplings', 1, 'Delicious apple dumplings', 16.00, 1),
('Strawberry dumplings', 1, 'Strawberry strawberry dumplings', 15.00, 1),
('Orange dumplings', 1, 'Delicious orange dumplings', 20.00, 2),
('Pancakes with chocolate', 1, 'Delicious pancakes with chocolate', 27.00, 2),
('Pancakes with powdered sugar', 1, 'Delicious pancakes with powdered sugar', 15.00, 3),
('Pancakes with strawberries', 1, 'Delicious pancakes with strawberries', 16.00, 3),
('Pasta with cheese and spinach', 2, 'Delicious pasta with cheese and spinach', 30.00, 1),
('Chicken quarter with roasted potatoes', 2, 'Delicious Chicken quarter with roasted potatoes', 35.00, 1),
('Pasta with tuna and tomatoes', 2, 'Delicious pasta with tuna and tomatoes',27.00, 1),
('Chicken leg with potatoes', 2, 'Delicious chicken leg with potatoes', 35.00, 1),
('Pasta with tomatoes and basil', 2, 'Delicious pasta with tomatoes and basil', 26.00, 2),
('Pasta with tomatoes and cheese', 2, 'Delicious Pasta with tomatoes and cheese', 28.00, 3),
('Pasta with broccoli sauce', 2, 'Delicious pasta with broccoli sauce', 29.00, 2),
('Chicken with potatoes', 2, 'Delicious chicken with potatoes', 35.00, 3),
('Chicken with french fries', 2, 'Delicious chicken with fries', 35.00, 2),
('Pork chop with cabbage and mushrooms', 2, 'Delicious pork chop with cabbage and mushrooms', 35.00, 3),
('Tomato soup with noodles', 3, 'Delicious tomato soup with noodles', 15.00, 1),
('Broth with noodles', 3, 'Delicious broth with noodles', 15.00, 1),
('Krupnik', 3, 'Delicious krupnik', 15.00, 2),
('Vegetable soup', 3, 'Delicious vegetable soup', 15.00, 2),
('Ramen soup', 3, 'Delicious ramen soup', 15.00, 3),
('Coca-cola', 4, 'Delicious coca-cola', 7.00, 1),
('Apple juice', 4, 'Delicious apple juice', 7.00, 1),
('Fanta', 4, 'Delicious fanta', 7.00, 2),
('Orange juice', 4, 'Delicious orange juice', 7.00, 3),
('7up', 4, 'Delicious 7up', 7.00, 3),
('Ham and cheese sandwiches', 4, 'Delicious ham and cheese sandwiches', 10.00, 1),
('Panini with cheese', 4, 'Delicious panini with cheese', 10.00, 2),
('Salmon sandwiches', 4, 'Delicious salmon sandwiches', 9.00, 3),
('Sandwiches with egg paste', 4, 'Delicious sandwiches with egg paste', 9.00, 3);
