create table confirmation (
                              id bigserial primary key unique not null ,
                              code varchar(100) not null ,
                              user_id bigint references users(id) not null
);