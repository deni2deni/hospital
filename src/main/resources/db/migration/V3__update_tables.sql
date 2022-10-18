alter table user
    add column admission_date     timestamp not null,
    add column discharge_date     timestamp,
    add column final_diagnosis_id bigint,
    add column bill_id            bigint    not null,
    add column card_id            bigint    not null,
    add foreign key (final_diagnosis_id) references diagnosis (id),
    add foreign key (bill_id) references bill (id),
    add foreign key (card_id) references card (id);

alter table journal
    drop foreign key journal_ibfk_1,
    drop foreign key journal_ibfk_2,
    drop foreign key journal_ibfk_4,
    rename column patient_id to patient_user_id,
    rename column doctor_id to doctor_user_id,
    rename column treatment_doctor_id to treatment_doctor_user_id,
    add foreign key (patient_user_id) references user (id),
    add foreign key (doctor_user_id) references user (id),
    add foreign key (treatment_doctor_user_id) references user (id);

alter table card rename to payment_card;

drop table doctor;

drop table patient;