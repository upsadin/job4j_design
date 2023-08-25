create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax_plus()
returns trigger as
$$
begin
   update products
   set price = price * 1.20
   where id = (select id from inserted);
   return NEW;
end;
$$
language 'plpgsql';

create trigger tax_trigger
AFTER Insert on products
referencing new table as inserted
for each statement
execute procedure tax_plus();

insert into products(name, producer, count, price)
values ('bread', 'father', 15, 50);

create function plus_tax()
returns trigger as
$$
begin
if new.price is null then
raise exception 'price can not be null';
end if;
new.price = new.price * 1.2;
return new;
end;
$$
language 'plpgsql';

create or replace trigger tax_trigger_before
before insert on products
for each row
execute procedure plus_tax();

insert into products(name, producer, count, price)
values ('jogurt', 'milka', 5, 75);

select * from products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create function add_product()
returns trigger as
$$
  begin
  insert into history_of_price(name, price, date)
  values(NEW.name, NEW.price, current_date);
  return new;
  end;
$$
language 'plpgsql';

create trigger add_trigger
after insert on products
for each row
execute procedure add_product();

insert into products(name, producer, count, price)
values ('pepsi', 'pepsi & co', 25, 100);

select * from history_of_price;