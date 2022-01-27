create table categories (
                            id bigserial primary key unique not null ,
                            name varchar(100) not null ,
                            section varchar(100) not null ,
                            color varchar(100) not null ,
                            logo varchar(100) not null ,
    user_id bigint references users(id) not null
);
