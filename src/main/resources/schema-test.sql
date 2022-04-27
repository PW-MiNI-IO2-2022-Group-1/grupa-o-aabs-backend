create table address_entity
(
    id           bigint auto_increment
        primary key,
    city         varchar(255) not null,
    house_number varchar(255) not null,
    local_number varchar(255) null,
    street       varchar(255) not null,
    zip_code     varchar(255) not null
);

create table admin_entity
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null
);

create table doctor_entity
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null
);

create table patient_entity
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    pesel      varchar(255) not null,
    address_id bigint       null,
    constraint FKqyxalhmw4nslr5tbgchnt2r3q
        foreign key (address_id) references address_entity (id)
);

create table vaccination_slot_entity
(
    id        bigint auto_increment
        primary key,
    date      timestamp not null,
    doctor_id bigint    not null,
    constraint FKitug81pxbvj9ew04irusfknbp
        foreign key (doctor_id) references doctor_entity (id)
);

create table vaccine_entity
(
    id             bigint auto_increment
        primary key,
    disease        varchar(255) not null,
    name           varchar(255) not null,
    required_doses int          not null
);

create table vaccination
(
    id                  bigint auto_increment
        primary key,
    status              int    not null,
    patient_id          bigint not null,
    vaccination_slot_id bigint not null,
    vaccine_id          bigint not null,
    constraint FK7tpk3e30a7bvx1wajpoo2f3do
        foreign key (vaccine_id) references vaccine_entity (id),
    constraint FK8pyx79megga3d02judjn6l122
        foreign key (vaccination_slot_id) references vaccination_slot_entity (id),
    constraint FKf453f3a4of1pvsilq40ruj6g0
        foreign key (patient_id) references patient_entity (id)
);

create table vaccination_entity
(
    id                  bigint auto_increment
        primary key,
    status              varchar(255) not null,
    patient_id          bigint       not null,
    vaccination_slot_id bigint       not null,
    vaccine_id          bigint       not null,
    constraint UK_qsec9q7b9javkedhrdsa93yl7
        unique (vaccination_slot_id),
    constraint FK1vk1d39b06mfppon506haxy52
        foreign key (vaccination_slot_id) references vaccination_slot_entity (id),
    constraint FK98edoeglrce26eanyydr8fd96
        foreign key (vaccine_id) references vaccine_entity (id),
    constraint FKp0435t1321isipjkowt4ruo4w
        foreign key (patient_id) references patient_entity (id)
);

