create table role
(
    id   bigint auto_increment primary key,
    name varchar(256) not null
);

create table user
(
    id      bigint auto_increment primary key,
    name    varchar(256) not null,
    status  varchar(256) not null,
    role_id bigint       not null,
    FOREIGN KEY (role_id) REFERENCES role (id)
);

create table login
(
    id       bigint auto_increment primary key,
    email    varchar(256) not null,
    password varchar(256) not null,
    status   varchar(256) not null,
    user_id  bigint       not null,
    FOREIGN KEY (user_id) REFERENCES user (id)
);


create table diagnosis
(
    id   bigint auto_increment primary key,
    name varchar(256) not null
);

create table treatment
(
    id    bigint auto_increment primary key,
    name  varchar(256) not null,
    price bigint       not null
);

create table doctor
(
    id      bigint auto_increment primary key,
    user_id bigint not null,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

create table bill
(
    id     bigint auto_increment primary key,
    sum    bigint,
    status varchar(256)
);

create table card
(
    id               bigint auto_increment primary key,
    number           varchar(16)  not null,
    exp_date         varchar(5)   not null,
    card_holder_name varchar(256) not null
);

create table patient
(
    id                 bigint auto_increment primary key,
    admission_date     timestamp    not null,
    discharge_date     timestamp,
    status             varchar(256) not null,
    final_diagnosis_id bigint,
    user_id            bigint       not null,
    bill_id            bigint       not null,
    card_id            bigint       not null,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (final_diagnosis_id) REFERENCES diagnosis (id),
    FOREIGN KEY (bill_id) REFERENCES bill (id),
    FOREIGN KEY (card_id) REFERENCES card (id)
);

create table journal
(
    id                  bigint auto_increment primary key,
    date                timestamp    not null,
    patient_id          bigint       not null,
    doctor_id           bigint       not null,
    diagnosis_id        bigint       not null,
    treatment_doctor_id bigint       not null,
    treatment_id        bigint       not null,
    treatment_status    varchar(256) not null,
    FOREIGN KEY (patient_id) references patient (id),
    FOREIGN KEY (doctor_id) references doctor (id),
    FOREIGN KEY (diagnosis_id) references diagnosis (id),
    FOREIGN KEY (treatment_doctor_id) references doctor (id),
    FOREIGN KEY (treatment_id) references treatment (id)
);
