alter table user
    add column enabled bit,
    add column  credentials_non_expired bit,
    add column account_non_expired bit,
    add column account_non_blocked bit,
    add column username varchar(256),
    add column password varchar(256);

create table user_roles(
    user_id bigint primary key ,
    roles varchar(256),
    foreign key (user_id) references user(id)
);