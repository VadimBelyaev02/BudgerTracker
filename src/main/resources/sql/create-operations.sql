create table operations (
                            id bigserial primary key unique not null ,
                            created_date date not null ,
                            amount real not null ,
                            user_id bigint references users(id) not null ,
                            category_name varchar(100) references categories(name) not null
);