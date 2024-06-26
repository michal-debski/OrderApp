ALTER TABLE restaurant_owner
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES order_app_user (user_id);

ALTER TABLE client
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES order_app_user (user_id);

insert into order_app_user (user_name, email, password)
values ('test_owner', 'stefan@miastowa.com', '$2a$12$1tiT2hk0..ORq3vPyqdmx.jL.UzMsvV2MfmQIGICISy93GuG6heyu');
insert into order_app_user (user_name, email, password)
values ('agata_agrafka', 'agata@victi.com', '$2a$12$5.gRCK8XPgxLPWpSIigyD.kJ9xU0Lqe7Xcjy1oFE5AN72QWlx1LNS');
insert into order_app_user (user_name, email, password)
values ('tomasz_tomaszewski', 'tomasz@tomani.com', '$2a$12$TqWBMfQ8.28pTqEJfyN9AO6FeffrQ9Z6ZbiHNKznRlKy0k/Fn8ERS');
insert into order_app_user (user_name, email, password)
values ('test_client', 'john.doe@example.com', '$2a$12$g0AKPp1vSrx6XzW3f4e.lOU0dVujF.hs.02w6glwYWcRFzTarnZt6');
insert into order_app_user (user_name, email, password)
values ('alice_smith', 'alice.smith@example.com', '$2a$12$ikrfJ19bjitxI6lb5OcAV.T93BHj1huKTkJmULCcSfiKMIbJ6qewC');
insert into order_app_user (user_name, email, password)
values ( 'bob_johnson', 'bob.johnson@example.com', '$2a$12$SQRA75k2Y7fHIrPqTIcJ8OGZuFduAkhEUAkwJ0ZDWKUx1vQlbq1Xm');

insert into order_app_role (role_id, role) values (1,'CLIENT');
insert into order_app_role (role_id, role) values (2,'RESTAURANT_OWNER');

UPDATE restaurant_owner SET user_id = 1 WHERE restaurant_owner_id = 1;
UPDATE restaurant_owner SET user_id = 2 WHERE restaurant_owner_id = 2;
UPDATE restaurant_owner SET user_id = 3 WHERE restaurant_owner_id = 3;
UPDATE client SET user_id = 4 WHERE client_id = 1;
UPDATE client SET user_id = 5 WHERE client_id = 2;
UPDATE client SET user_id = 6 WHERE client_id = 3;

insert into order_app_user_role (user_id, role_id) values (1, 2), (2, 2), (3, 2), (4, 1);
insert into order_app_user_role (user_id, role_id) values (5, 1), (6,1);

