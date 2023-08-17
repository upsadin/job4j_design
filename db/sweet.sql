create table sweet(
	id serial primary key,
	name varchar(255),
	cal int,
	price float
);
insert into sweet (name, cal, price) values ('donat', 200, 55.5);
update sweet set price = 70.35;
delete from sweet;