create table cloud(
	id serial primary key,
	size float
);

create table users(
	phone varchar(255) primary key,
	name varchar(255),
	cloud_id int references cloud(id)
);

insert into cloud(size) values(2);
insert into users(phone, name, cloud_id)
values('+79811111111', 'Pavel', 1);

create table insurance(
	id serial primary key,
	name varchar(255),
	type varchar(255)
);
create table cars(
	id serial primary key,
	brand varchar(255)
);

create table insurance_cars(
	id serial primary key,
	insurance_id int references insurance(id),
	car_id int references cars(id)
);

insert into insurance (name, type) values('Tinkoff', 'OSAGO');
insert into insurance (name, type) values('RESO', 'KASKO');

insert into cars (brand) values ('KIA');
insert into cars (brand) values ('LADA');

insert into insurance_cars (insurance_id, car_id) values (1, 1);
insert into insurance_cars (insurance_id, car_id) values (2, 2);
insert into insurance_cars (insurance_id, car_id) values (1, 2);

create table game_nickname(
	id serial primary key,
	name varchar(255)
);
create table game_users(
	id serial primary key,
	name varchar(255),
	nickname_id int references game_nickname(id) unique
);

insert into game_nickname (name) values ('Madmag');
insert into game_users (name, nickname_id) values ('Pavel', 1);

create table game_nickname2(
	id serial primary key,
	name varchar(255)
);
create table game_users2(
	id serial primary key,
	name varchar(255)
);
create table users_game(
	id serial primary key,
	user_id int references game_users(id) unique,
	nickname_id int references game_nickname(id) unique
);

insert into game_nickname2 (name) values ('Madmag2');
insert into game_users2 (name) values ('Pavel2');
insert into users_game (user_id, nickname_id) values (1, 1);