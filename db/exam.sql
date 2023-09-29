CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);


CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);


insert into company VALUES(1, 'Company 1');
insert into company VALUES(2, 'Company 2');
insert into company VALUES(3, 'Company 3');
insert into company VALUES(4, 'Company 4');
insert into company VALUES(5, 'Company 5');

insert into person VALUES(1, 'Ben', 1);
insert into person VALUES(2, 'Mark',1 );
insert into person VALUES(3, 'Julia', 4);
insert into person VALUES(4, 'Kevin', null);
insert into person VALUES(5, 'Shen', 3);
insert into person VALUES(6, 'Josh', 3);
insert into person VALUES(7, 'Barbara', 5);


SELECT p.name, c.name from person p left join company c on p.company_id=c.id where p.company_id != 5 or p.company_id is null;

SELECT c.name, count(c.name) as cnt
    from company as c join person as p on p.company_id=c.id
	GROUP BY c.name having count(c.name) = max((SELECT count(c.name)
    from company as c join person as p on p.company_id=c.id
	GROUP BY c.name limit 1));