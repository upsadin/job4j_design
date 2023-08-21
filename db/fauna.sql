create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

select * from fauna;
insert into fauna (name, avg_age, discovery_date) values ('Butfly', 25, '1921-06-01');
insert into fauna (name, avg_age, discovery_date) values ('glue_fish', 300, '1999-12-12');
insert into fauna (name, avg_age, discovery_date) values ('green_bull', 15000, '1955-05-07');
insert into fauna (name, avg_age) values ('hi_monkey', 20999);
select * from fauna where discovery_date < '1950-01-01';
select * from fauna where name like '%fish' or name like 'fish%' or name like '%fish%';
select * from fauna where discovery_date is null;
select * from fauna where avg_age > 10000 and avg_age < 21000 order by avg_age desc;
