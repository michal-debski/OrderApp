insert into order_app_user (user_id, user_name, email, password)
values (1, 'stefan_stefanowicz', 'stefan@miastowa.com', '$2a$12$i6K9EV5/U8zWs7okpKA0Y.AR9ekgNppwmSRzNOo9rsg6SkYecEGma');
insert into order_app_user (user_id, user_name, email, password)
values (2, 'agata_agrafka', 'agata@victi.com', '$2a$12$5.gRCK8XPgxLPWpSIigyD.kJ9xU0Lqe7Xcjy1oFE5AN72QWlx1LNS');
insert into order_app_user (user_id, user_name, email, password)
values (3, 'tomasz_tomaszewski', 'tomasz@tomani.com', '$2a$12$TqWBMfQ8.28pTqEJfyN9AO6FeffrQ9Z6ZbiHNKznRlKy0k/Fn8ERS');
insert into order_app_user (user_id, user_name, email, password)
values (4, 'john_doe', 'john.doe@example.com', '$2a$12$OV8dLl8BL9nx.JYkiTBnqu7N7UImtzo/T0o9jP4ncP/7W7x4fbth6');
insert into order_app_user (user_id, user_name, email, password)
values (5, 'alice_smith', 'alice.smith@example.com', '$2a$12$ikrfJ19bjitxI6lb5OcAV.T93BHj1huKTkJmULCcSfiKMIbJ6qewC');
insert into order_app_user (user_id, user_name, email, password)
values (6, 'bob_johnson', 'bob.johnson@example.com', '$2a$12$SQRA75k2Y7fHIrPqTIcJ8OGZuFduAkhEUAkwJ0ZDWKUx1vQlbq1Xm');

insert into order_app_role (role_id, role) values (1,'CLIENT');
insert into order_app_role (role_id, role) values (2,'RESTAURANT_OWNER');

insert into order_app_user_role (user_id, role_id) values (1, 2), (2, 2), (3, 2), (4, 1);
insert into order_app_user_role (user_id, role_id) values (5, 1), (6,1);

