create table meetings(
                         id serial primary key,
                         name varchar(255)
);

insert into meetings(name) values('Meeting1');
insert into meetings(name) values('Meeting2');
insert into meetings(name) values('Meeting3');
insert into meetings(name) values('Meeting4');
insert into meetings(name) values('Meeting5');


create table users(
                      id serial primary key,
                      name varchar(255)
);

insert into users(name) values('User1');
insert into users(name) values('User2');
insert into users(name) values('User3');
insert into users(name) values('User4');
insert into users(name) values('User5');

create table request_status(
                               id serial primary key,
                               name varchar(255)
);

insert into request_status(name) values('accept');
insert into request_status(name) values('decline');

create table request(
                        id serial primary key,
                        meeting_id int references meetings(id),
                        user_id int references users(id),
                        request_status_id int references request_status(id)
);

insert into request(meeting_id, user_id, request_status_id) values(1, 1, 2);
insert into request(meeting_id, user_id, request_status_id) values(1, 2, 2);
insert into request(meeting_id, user_id, request_status_id) values(1, 3, 2);
insert into request(meeting_id, user_id, request_status_id) values(1, 4, 2);
insert into request(meeting_id, user_id, request_status_id) values(1, 5, 2);

insert into request(meeting_id, user_id, request_status_id) values(3, 1, 2);
insert into request(meeting_id, user_id, request_status_id) values(3, 2, 2);
insert into request(meeting_id, user_id, request_status_id) values(3, 3, 2);
insert into request(meeting_id, user_id, request_status_id) values(3, 4, 2);
insert into request(meeting_id, user_id, request_status_id) values(3, 5, 2);

insert into request(meeting_id, user_id, request_status_id) values(2, 1, 1);
insert into request(meeting_id, user_id, request_status_id) values(2, 2, 2);
insert into request(meeting_id, user_id, request_status_id) values(2, 3, 1);
insert into request(meeting_id, user_id, request_status_id) values(2, 4, 2);
insert into request(meeting_id, user_id, request_status_id) values(2, 5, 1);

insert into request(meeting_id, user_id, request_status_id) values(4, 1, 2);
insert into request(meeting_id, user_id, request_status_id) values(4, 2, 2);
insert into request(meeting_id, user_id, request_status_id) values(4, 3, 2);
insert into request(meeting_id, user_id, request_status_id) values(4, 4, 1);
insert into request(meeting_id, user_id, request_status_id) values(4, 5, 1);

insert into request(meeting_id, user_id, request_status_id) values(5, 1, 1);
insert into request(meeting_id, user_id, request_status_id) values(5, 2, 1);
insert into request(meeting_id, user_id, request_status_id) values(5, 3, 1);
insert into request(meeting_id, user_id, request_status_id) values(5, 4, 1);
insert into request(meeting_id, user_id, request_status_id) values(5, 5, 1);

select m.name, count(r.user_id) from meetings m left join request r on m.id = r.meeting_id and request_status_id = 1
group by m.id
order by m.id;

insert into meetings(name) values('meeting');

select m.name,count(r.user_id) from meetings m left join request r on m.id = r.meeting_id
group by m.id
having count(r.user_id) = 0
order by m.id;