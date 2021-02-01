create table post (
    id serial primary key,
    link text unique,
    nameVac text,
    created date,
    description text not null
)