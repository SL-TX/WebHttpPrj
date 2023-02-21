create table cars
(
    id    bigint       not null primary key,
    price integer      not null,
    brand varchar(255) not null,
    model varchar(255) not null
);
create table humans
(
    id          bigint       not null primary key,
    age         integer      not null,
    name        varchar(255) not null,
    have_rights bool         not null,
    car_id      integer      not null
        constraint humans_cars_id_fk
            references cars
);