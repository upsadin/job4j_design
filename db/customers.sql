CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values
('Pavel', 'Pavlov', 36, 'St.Petersburg'),
('Igor', 'Smelov', 40, 'Moscow'),
('Inna', 'Smirnova', 27, 'Saratov'),
('Zhanna', 'Pak', 20, 'St.Petersburg');

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values
(2025, 1), (20000, 4), (7400, 4), (500, 3);

select * from customers c where c.id not in(select o.customer_id from orders o);