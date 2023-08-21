create table nicknames(
    id serial primary key,
    name text
);

create table users(
    id serial primary key,
    name text,
    paid bool,
    nickname_id int references nicknames(id)
);

insert into nicknames(name) values('Brog');
insert into nicknames(name) values('Dargoon');
insert into nicknames(name) values('Biden');

insert into users(name, paid, nickname_id) values('Pavel', false, 1);
insert into users(name, paid, nickname_id) values('Sanya', false, 2);
insert into users(name, paid, nickname_id) values('Zina', true, 3);
insert into users(name, paid) values('Jimmy', false);
insert into users(name, paid) values('Igor', false);

select * from users;
select * from users inner join nicknames on users.nickname_id = nicknames.id;

select * from users as u join nicknames as n on u.nickname_id = n.id;
select u.name from users as u join nicknames as n on u.nickname_id = n.id;

select u.name as Имя, u.paid as "Платный аккаунт"
from users as u join nicknames as n on u.nickname_id = n.id;

select u.name as Имя, u.paid as "Платный аккаунт", n.id as Numeric
from users as u join nicknames as n on u.nickname_id = n.id;