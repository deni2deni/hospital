alter table payment_card
    modify number varchar(19),
    modify exp_date varchar(7),
    add column cvc varchar(3);