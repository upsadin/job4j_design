create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('седан'), ('хэтчбек'), ('пикап'), ('кроссовер'), ('внедорожник');
insert into car_engines(name) values('карбюратор'), ('инжектор'), ('дизель'), ('электрический'),  ('гибрид');
insert into car_transmissions(name) values('механическая'), ('автомат'), ('вариатор');
insert into cars(name, body_id, engine_id, transmission_id) values
    ('Модель 1', 1, 1, 1), ('Модель 2', 2, 2, 2), ('Модель 3', 3, 3, 1), ('Модель 4', 2, 4, 2), ('Модель 5', 1, 1, 2);
SELECT c.id, c.name, cb.name, ce.name, ct.name FROM cars c full join car_bodies cb on c.body_id = cb.id
    full join car_engines ce on c.engine_id = ce.id
    full join car_transmissions ct on c.transmission_id = ct.id;
SELECT cb.name as "Кузов не используется" FROM car_bodies cb left join cars c on c.body_id = cb.id where c.name is null;
SELECT ce.name as "Двигатель не используется" FROM car_engines ce left join cars c on c.engine_id = ce.id where c.name is null;
SELECT ct.name as "Коробка не используется" FROM car_transmissions ct left join cars c on c.transmission_id = ct.id where c.name is null;