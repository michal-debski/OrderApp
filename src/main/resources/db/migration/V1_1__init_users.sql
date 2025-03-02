
insert into order_app_user (user_name, name, surname, email, phone, password)
values ('test_owner', 'Stefan','Stefanowicz','stefan@miastowa.com','+48 000 111 222','$2a$12$1tiT2hk0..ORq3vPyqdmx.jL.UzMsvV2MfmQIGICISy93GuG6heyu');
insert into order_app_user (user_name, name, surname, email, phone, password)
values ('agata_agrafka', 'Agata','Agrafka','agata@victi.com', '+48 111 222 333','$2a$12$5.gRCK8XPgxLPWpSIigyD.kJ9xU0Lqe7Xcjy1oFE5AN72QWlx1LNS');
insert into order_app_user (user_name, name, surname, email, phone, password)
values ('tomasz_tomaszewski','Tomasz','Tomaszewski', 'tomasz@tomani.com','+48 444 555 666' ,'$2a$12$TqWBMfQ8.28pTqEJfyN9AO6FeffrQ9Z6ZbiHNKznRlKy0k/Fn8ERS');
insert into order_app_user (user_name, name, surname, email, phone, password)
values ('test_client', 'John','Doe','john.doe@example.com', '+48 777 888 999','$2a$12$g0AKPp1vSrx6XzW3f4e.lOU0dVujF.hs.02w6glwYWcRFzTarnZt6');
insert into order_app_user (user_name, name, surname, email, phone, password)
values ('alice_smith','Alice' ,'Smith','alice.smith@example.com','+48 010 202 303', '$2a$12$ikrfJ19bjitxI6lb5OcAV.T93BHj1huKTkJmULCcSfiKMIbJ6qewC');
insert into order_app_user (user_name, name, surname, email, phone, password)
values ( 'bob_johnson','Bob','Johnson', 'bob.johnson@example.com', '+48 202 305 405','$2a$12$SQRA75k2Y7fHIrPqTIcJ8OGZuFduAkhEUAkwJ0ZDWKUx1vQlbq1Xm');

insert into order_app_role (role_id, role) values (1,'CLIENT');
insert into order_app_role (role_id, role) values (2,'RESTAURANT_OWNER');

insert into order_app_user_role (user_id, role_id) values (1, 2), (2, 2), (3, 2), (4, 1);
insert into order_app_user_role (user_id, role_id) values (5, 1), (6,1);

