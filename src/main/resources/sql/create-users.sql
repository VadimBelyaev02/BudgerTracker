create table users (
                       id bigserial primary key unique not null ,
                       nickname varchar(100) unique not null ,
                       password varchar(100) not null ,
                       email varchar(100) unique not null ,
                       created_date date not null ,
                       role varchar(20) not null ,
                       confirmed boolean not null ,
                       mode varchar(30) not null ,
                       currency varchar(30) not null ,
                       language varchar(50) not null
);