create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iphone x', 300.0),
('iphone 14', 999.99), ('sumsung s21', 867.55);

insert into people(name) values('Pavel'), ('Vika'), ('Gosha');
insert into devices_people(device_id, people_id) values
(1, 2), (2, 1), (3,3);

select avg(price) from devices;

insert into devices(name, price) values ('Apple watch', 250.44),
('JBL-5', 350.20), ('PC', 2005.60), ('Coffee machine', 650.49);
insert into devices_people(device_id, people_id) values
(4, 1), (5 ,2), (6, 3);

select p.name, avg(d.price) from devices_people as dp join people p on dp.people_id=p.id
join devices d on dp.device_id=d.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp join people p on dp.people_id=p.id
join devices d on dp.device_id=d.id
group by p.name having avg(d.price) >600;

SELECT table_name FROM information_schema.tables WHERE table_schema='public';

