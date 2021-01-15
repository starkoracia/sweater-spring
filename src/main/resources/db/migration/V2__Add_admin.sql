insert into usr(id, active, password, username, email)
values (1, true, '244268', 'admin', 'starkoracia@gmail.com');

insert into user_role(user_id, roles)
values (1, 'USER'),
       (1, 'ADMIN');