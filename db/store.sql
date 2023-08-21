create table type(
    id serial primary key,
    name text
);
create table product(
    id serial primary key,
    name text,
    type_id int references type,
    expired_date timestamp,
    price float
);

insert into type(name)
values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('НАПИТКИ');

insert into product(name, type_id, expired_date, price)
values ('Российский', 1, '2023-07-30', 600), ('Гауда', 1,  '2023-09-15', 734.50),
('Пармезан', 1, '2023-08-14', 1005.60), ('Дружба', 1, '2023-05-07', 444.44);
insert into product(name, type_id, expired_date, price)
values ('Молоко пастеризованное', 2, '2023-08-20', 90), ('Кефир', 2, '2023-08-07', 95.0),
('Сметана', 2, '2023-08-12', 65.55), ('Мороженое ванильное', 2, '2023-08-28', 55.55);
insert into product(name, type_id, expired_date, price)
values ('Салями', 3, '2023-09-02', 666.66), ('Докторская', 3, '2023-10-12', 350),
('Краковская', 3, '2023-08-21', 560.47), ('Особая', 3, '2023-12-12', 400.12);
insert into product(name, type_id, expired_date, price)
values ('Тархун', 4, '2023-12-14', 80), ('Доброкола', 4, '2023-12-03', 88.7),
('Боржоми', 4, '2023-12-16', 100.01), ('Сок добрый', 4, '2023-08-19', 120);

select p.name from product p join type t on p.type_id=t.id where lower(t.name) = 'сыр';

select * from product where lower(name) like 'мороженое%'
or lower(name) like '%мороженое' or lower(name) like '%мороженое%';

select * from product where expired_date > current_date;

select name, price from product where price = (select max(price) from product);

select t.name as Имя_типа, count(1) from product p join type t on p.type_id=t.id group by t.name;

select * from product p join type t on p.type_id=t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';

select t.name from product p join type t on p.type_id=t.id group by t.name having count(1) < 10;

select * from product p join type t on p.type_id=t.id;

select p.name, t.name from product p join type t on p.type_id=t.id;