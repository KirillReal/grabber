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
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company values (1, 'Microsoft');
insert into company values (2, 'Apple');
insert into company values (3, 'BMW');
insert into company values (4, 'Ford');
insert into company values (5, 'Intel');

insert into person values (1, 'John', 1);
insert into person values (2, 'Ben', 1);
insert into person values (3, 'James', 2);
insert into person values (4, 'Oliver', 2);
insert into person values (5, 'Danny');
insert into person values (6, 'Jordan', 3);
insert into person values (7, 'Paul', 3);
insert into person values (8, 'Frank');
insert into person values (9, 'Alexander', 3);
insert into person values (10, 'Corey', 5);
insert into person values (11, 'Ronnie', 5);

select p.name,c.name from person as p left join company c on p.company_id = c.id
where p.company_id != 5 or p.company_id is null;

select   total.name, total.countPerson  from (
    select company.name, count(person.id) as countPerson  from person
    join company on person.company_id = company.id
    group by company.name) AS total where total.countPerson = (SELECT MAX(total.countPerson) from (
    select company.name, count(person.id) as countPerson  from person
    join company on person.company_id = company.id group by company.name) AS total);
