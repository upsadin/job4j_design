CREATE table departments (
  id serial primary key,
  name varchar(255)
);

CREATE table employees (
  id serial primary key,
  name varchar(255),
  departments_id int REFERENCES departments(id)
);

insert into departments(name) values('IT'), ('PR'), ('HR');
insert into employees(name, departments_id) values('Pavel', 1), ('Zhenya', 1), ('Igor', 3), ('Sasha', null);

select * from employees e left join departments d on e.departments_id = d.id;
select * from employees e right join departments d on e.departments_id = d.id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;
select d.name from departments d left join employees e on e.departments_id = d.id where e.name is null;

select d.name,e.name from employees e left join departments d on e.departments_id = d.id;
select d.name,e.name from departments d right join employees e on e.departments_id = d.id;

create table gender(
    id serial primary key,
    name varchar(255)
);

create table teen(
    id serial primary key,
    name varchar(255),
    gender name varchar(255)
);

insert into teen(name, gender) values('Lisa', 'woman'),
('Sasha', 'man'), ('Nastya', 'woman'), ('Zoya', 'woman'),
('Denis', 'man'), ('Vika', 'woman'), ('Olya', 'woman'),
('Masha', 'woman'), ('Vlad', 'man'), ('Mark', 'man');

SELECT DISTINCT t1.name, t1.gender, t2.name, t2.gender FROM teen t1 CROSS JOIN teen t2 where t1.gender != t2.gender
and t1.gender = 'man';