create table users (
                       id bigserial primary key  ,
                       nickname varchar(100)  ,
                       password varchar(100) ,
                       email varchar(100)    ,
                       created_date date   ,
                       role varchar(20)   ,
                       confirmed boolean   ,
                       mode varchar(30)   ,
                       currency varchar(30)  ,
                       language varchar(50)
);

-- create table users_test (
--     id bigserial primary key
-- );
-- insert into users_test (id) values (1)