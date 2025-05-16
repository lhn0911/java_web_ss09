# drop database java_web_ss09;
create database java_web_ss09;
use java_web_ss09;

create table customer (
                          id bigint primary key auto_increment,
                          username varchar(200) not null,
                          password varchar(200) not null,
                          email varchar(200) not null,
                          phone varchar(200) not null,
                          address varchar(200) not null,
                          gender varchar(200) not null
);

insert into customer(id, username, password, email, phone, address, gender)
values (1, 'admin', '123', 'admin@gmail.com', '0335128005', 'ha noi', 'nam');

delimiter //
create procedure login(c_username varchar(200), c_password varchar(200))
begin
    select * from customer where username = c_username and password = c_password;
end;
//
delimiter ;

create table movies (
                        id bigint primary key auto_increment,
                        title varchar(100),
                        director varchar(100),
                        genre varchar(50),
                        description text,
                        duration int,
                        language varchar(50)
);

insert into movies(title, director, genre, description, duration, language)
values
    ('avengers: endgame', 'anthony russo', 'hành động', 'trận chiến cuối cùng của các siêu anh hùng.', 180, 'english'),
    ('mắt biếc', 'victor vũ', 'tình cảm', 'chuyện tình buồn giữa ngạn và hà lan.', 120, 'tiếng việt');

delimiter //
create procedure find_all_movie()
begin
    select * from movies;
end;
delimiter //
delimiter //
create procedure find_ById(c_id bigint)
begin
    select * from movies where id = c_id;
end;
delimiter //

create table screenrooms (
                             id bigint primary key auto_increment,
                             screen_room_name varchar(100),
                             total_seat int
);

insert into screenrooms(screen_room_name, total_seat)
values
    ('phòng 1', 100),
    ('phòng 2', 120),
    ('phòng 3', 90);

create table schedules (
                           id bigint primary key auto_increment,
                           movie_id bigint,
                           show_time datetime,
                           screen_room_id bigint,
                           available_seats int,
                           format varchar(20),
                           foreign key (movie_id) references movies(id),
                           foreign key (screen_room_id) references screenrooms(id)
);

insert into schedules(movie_id, show_time, screen_room_id, available_seats, format)
values
    (1, '2025-05-16 18:00:00', 1, 80, '2d'),
    (1, '2025-05-17 20:00:00', 2, 60, '3d'),
    (2, '2025-05-18 19:30:00', 3, 70, '2d');

create table seats (
                       id bigint primary key auto_increment,
                       screen_room_id bigint,
                       seat_number varchar(10),
                       price double default 50000,
                       status varchar(20),
                       foreign key (screen_room_id) references screenrooms(id)
);

create table tickets (
                         id bigint primary key auto_increment,
                         customer_id bigint,
                         schedule_id bigint,
                         total_money double,
                         created_at datetime,
                         foreign key (customer_id) references customer(id),
                         foreign key (schedule_id) references schedules(id)
);

create table ticket_seats (
                              ticket_id bigint,
                              seat_id bigint,
                              primary key(ticket_id, seat_id),
                              foreign key (ticket_id) references tickets(id),
                              foreign key (seat_id) references seats(id)
);

delimiter //
create procedure find_all_screenrooms()
begin
    select id, screen_room_name, total_seat from screenrooms;
end;
//
delimiter ;

delimiter //
create procedure find_schedules_by_movieid(in m_id bigint)
begin
    select s.id,
           m.title as movie_title,
           s.show_time,
           s.screen_room_id,
           s.available_seats,
           s.format
    from schedules s
             join movies m on s.movie_id = m.id
    where m.id = m_id
    order by s.show_time asc;
end;
//
delimiter ;

delimiter //
create procedure add_ticket(
    in c_customer_id bigint,
    in c_schedule_id bigint,
    in c_total_money double
)
begin
    insert into tickets(customer_id, schedule_id, total_money, created_at)
    values (c_customer_id, c_schedule_id, c_total_money, now());

    select last_insert_id() as new_ticket_id;
end;
//
delimiter ;

delimiter //
create procedure add_ticket_seat(
    in c_ticket_id bigint,
    in c_seat_id bigint
)
begin
    insert into ticket_seats(ticket_id, seat_id)
    values (c_ticket_id, c_seat_id);
end;
//
delimiter ;

delimiter //
create procedure update_seat_status(
    in c_seat_id bigint,
    in new_status varchar(20)
)
begin
    update seats
    set status = new_status
    where id = c_seat_id;
end;
//
delimiter ;

delimiter //
create procedure decrease_available_seats(
    in c_schedule_id bigint,
    in seat_count int
)
begin
    update schedules
    set available_seats = available_seats - seat_count
    where id = c_schedule_id;
end;
//
delimiter ;

delimiter //
create procedure find_seats_by_screenroom(
    in c_screen_room_id bigint
)
begin
    select * from seats
    where screen_room_id = c_screen_room_id;
end;
//
delimiter ;

delimiter //
create procedure find_booked_seats_by_schedule(
    in c_schedule_id bigint
)
begin
    select s.*
    from seats s
             join ticket_seats ts on s.id = ts.seat_id
             join tickets t on ts.ticket_id = t.id
    where t.schedule_id = c_schedule_id;
end;
//
delimiter ;

delimiter //
create procedure find_seats_by_ids(IN seat_ids TEXT)
begin
    select * from seats
    where FIND_IN_SET(id, seat_ids);
end //
delimiter ;
delimiter //
create procedure find_schedule_byId(in c_id bigint)
begin
    select *
    from schedules
    where id = c_id;
end;
//
delimiter ;
