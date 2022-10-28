alter table user
    drop foreign key user_ibfk_3,
    drop foreign key user_ibfk_4,
    drop column bill_id,
    drop column card_id;

alter table bill
    add user_id bigint not null;

alter table payment_card
    add user_id bigint not null;