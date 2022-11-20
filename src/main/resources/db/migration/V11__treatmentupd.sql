create table treatment_type
(
    id    bigint auto_increment primary key,
    name  varchar(256) not null,
    price bigint       not null
);

alter table treatment
    drop column name,
    drop column price,
    add column treatment_type_id bigint not null ,
    add column treatment_status varchar(256) not null;

insert into treatment_type(name, price) values ("surgeon","1000");
insert into treatment_type(name, price) values ("procedures","500");
insert into treatment_type(name, price) values ("medicine","100");