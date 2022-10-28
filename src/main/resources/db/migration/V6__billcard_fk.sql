alter table bill
    add foreign key (user_id) references user(id);

alter table payment_card
    add foreign key (user_id) references user(id);