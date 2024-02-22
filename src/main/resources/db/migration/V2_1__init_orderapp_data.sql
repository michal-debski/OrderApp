INSERT INTO address (country, city, postal_code, street)
VALUES
('Poland', 'Warsaw', '00-001', '123 Main Street'),
('Poland', 'Krakow', '30-123', '456 Elm Street'),
('Poland', 'Gdansk', '80-987', '789 Oak Street'),
('Poland', 'Poznan', '61-789', '321 Pine Street'),
('Poland', 'Wroclaw', '50-456', '654 Maple Street'),
('Poland', 'Katowice', '40-234', '987 Birch Street');

INSERT INTO CART (TOTAL_PRICE) VALUES
(NULL),
(NULL),
(NULL),
(NULL),
(NULL);

insert into client (name, surname, phone, email, address_id, cart_id)
values
('John', 'Doe', '123-456-789', 'john.doe@example.com', 1, 1),
('Alice', 'Smith', '987-654-321', 'alice.smith@example.com', 2, 2),
('Bob', 'Johnson', '555-555-555', 'bob.johnson@example.com', 3, 3);

insert into restaurant_owner(name, surname, email)
values
('Stefan', 'Stefanowicz','stefan@miastowa.com'),
('Agata', 'Agrafka', 'agata@victi.com'),
('Tomasz', 'Tomaszewski', 'tomasz@tomani.com');


INSERT INTO RESTAURANT(NAME, RESTAURANT_OWNER_ID)
VALUES
('Miastowa', 1),
('Victi', 2),
('Tomani', 3);




insert into meal(name, category, description, price, restaurant_id)
values
('Apple dumplings', 'Desserts', 'Delicious apple dumplings', 16.00, 1),
('Strawberry dumplings', 'Desserts', 'Strawberry strawberry dumplings', 15.00, 1),
('Orange dumplings', 'Desserts', 'Delicious orange dumplings', 20.00, 2),
('Pancakes with chocolate', 'Desserts', 'Delicious pancakes with chocolate', 27.00, 2),
('Pancakes with powdered sugar', 'Desserts', 'Delicious pancakes with powdered sugar', 15.00, 3),
('Pancakes with strawberries', 'Desserts', 'Delicious pancakes with strawberries', 16.00, 3),
('Pasta with cheese and spinach', 'Main dish', 'Delicious pasta with cheese and spinach', 30.00, 1),
('Chicken quarter with roasted potatoes', 'Main dish', 'Delicious Chicken quarter with roasted potatoes', 35.00, 1),
('Pasta with tuna and tomatoes', 'Main dish', 'Delicious pasta with tuna and tomatoes',27.00, 1),
('Chicken leg with potatoes', 'Main dish', 'Delicious chicken leg with potatoes', 35.00, 1),
('Pasta with tomatoes and basil', 'Main dish', 'Delicious pasta with tomatoes and basil', 26.00, 2),
('Pasta with tomatoes and cheese', 'Main dish', 'Delicious Pasta with tomatoes and cheese', 28.00, 3),
('Pasta with broccoli sauce', 'Main dish', 'Delicious pasta with broccoli sauce', 29.00, 2),
('Chicken with potatoes', 'Main dish', 'Delicious chicken with potatoes', 35.00, 3),
('Chicken with french fries', 'Main dish', 'Delicious chicken with fries', 35.00, 2),
('Pork chop with cabbage and mushrooms', 'Main dish', 'Delicious pork chop with cabbage and mushrooms', 35.00, 3),
('Tomato soup with noodles', 'Soup', 'Delicious tomato soup with noodles', 15.00, 1),
('Broth with noodles', 'Soup', 'Delicious broth with noodles', 15.00, 1),
('Krupnik', 'Soup', 'Delicious krupnik', 15.00, 2),
('Vegetable soup', 'Soup', 'Delicious vegetable soup', 15.00, 2),
('Ramen soup', 'Soup', 'Delicious ramen soup', 15.00, 3),
('Coca-cola', 'Drink', 'Delicious coca-cola', 7.00, 1),
('Apple juice', 'Drink', 'Delicious apple juice', 7.00, 1),
('Fanta', 'Drink', 'Delicious fanta', 7.00, 2),
('Orange juice', 'Drink', 'Delicious orange juice', 7.00, 3),
('7up', 'Drink', 'Delicious 7up', 7.00, 3),
('Ham and cheese sandwiches', 'Drink', 'Delicious ham and cheese sandwiches', 10.00, 1),
('Panini with cheese', 'Drink', 'Delicious panini with cheese', 10.00, 2),
('Salmon sandwiches', 'Drink', 'Delicious salmon sandwiches', 9.00, 3),
('Sandwiches with egg paste', 'Drink', 'Delicious sandwiches with egg paste', 9.00, 3);
