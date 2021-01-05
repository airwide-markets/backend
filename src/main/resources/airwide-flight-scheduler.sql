-- roles
create table roles (role_id text not null, role_name text, primary key (role_id));
insert into roles (role_id, role_name) values('student','student');
insert into roles (role_id, role_name) values('instructor','instructor');
insert into roles (role_id, role_name) values('chief_instructor','chief_instructor');
insert into roles (role_id, role_name) values('operator','operator');
-- users
create table users (user_id text not null, user_phone text, user_email text, user_first_name text, user_surname text,
                     user_role text, user_photo_url text not null default 'default.png', primary key (user_id));
insert into users (user_id, user_phone, user_email, user_first_name, user_surname, user_role)
values ('123456', '27729745087','david.tekeshe@gmail.com','David','Tekeshe', 'student');
insert into users (user_id, user_phone, user_email, user_first_name, user_surname, user_role)
values ('123457', '27729745088','f13ray@gmail.com','Godfrey','Rayner', 'student');
-- alter table users add column user_photo_url text not null default 'default.png';
-- aircrafts
create table aircrafts (registration_number text not null, year_manufactured varchar(4), aircraft_make text,
                         aircraft_model text, aircraft_category text, track_oil_and_fuel varchar (1) default 'N',
                         identify_meter_mismatch varchar (1) default 'N', primary key (registration_number));
insert into aircrafts (registration_number, year_manufactured, aircraft_make, aircraft_model, aircraft_category)
values ('N1231','1978','Cessna','C172','Aircraft');
insert into aircrafts (registration_number, year_manufactured, aircraft_make, aircraft_model, aircraft_category)
values ('N1232','1978','Cessna','C172','Aircraft');
insert into aircrafts (registration_number, year_manufactured, aircraft_make, aircraft_model, aircraft_category)
values ('N1233','1978','Cessna','C172','Aircraft');
insert into aircrafts (registration_number, year_manufactured, aircraft_make, aircraft_model, aircraft_category)
values ('N1235','1978','PA','PA-29','Aircraft');
-- reservations
create table reservations (student_id text not null, registration_number text not null, instructor_id text,
                           start_date timestamp, end_date timestamp, is_confirmed varchar(1) default 'N',
                           primary key (student_id, registration_number, start_date));
insert into reservations (student_id, registration_number, instructor_id, start_date, end_date)
values ('123457', 'N1231','123456','2019-11-02 14:00', '2019-11-02 16:00');
-- calendar events
create table schedule (
	       flight_school_id text not null,
	       student_id text not null, 
	       instructor_id text not null,
	       aircraft_model text not null,
	       aircraft_reg_number text not null,
	       uuid text not null, 
               start_date timestamp,
   	       end_date timestamp,
	       description text,
	       status text,
	       primary key (flight_school_id, student_id, instructor_id, aircraft_model, aircraft_reg_number, uuid)
);
