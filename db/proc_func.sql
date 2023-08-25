create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create procedure deleteBYid(d_id integer)
language 'plpgsql' as
$$
  begin
    if not exists (select 1 from products where id = d_id)
    then raise exception 'ID doesn''t exist';
    end if;
    delete from products where id = d_id;
  end;
$$;

create or replace function deleteByname(d_name varchar(50))
returns boolean
language 'plpgsql' as
$$
declare rsl boolean default false;
  begin
    if not exists (select 1 from products where name = d_name)
    then raise exception 'name doesn''t exist';
    end if;
    delete from products where name = d_name;
    rsl = true;
    return rsl;
  end;
$$;

insert into products (name, producer, count, price)
values ('prod1', 'produc1', 5, 50);

insert into products (name, producer, count, price)
values ('prod2', 'produc3', 15, 500);

insert into products (name, producer, count, price)
values ('prod3', 'produc3', 25, 5000);

insert into products (name, producer, count, price)
values ('prod4', 'produc4', 35, 50000);

select * from products;

call deleteBYid(2);
select deleteByname('prod4');
select * from products;
