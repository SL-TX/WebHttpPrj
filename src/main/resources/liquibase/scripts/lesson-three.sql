-- liquibase formatted sql

-- changeset sltx-p1:1
create unique index student_uniq
    on student (name);

-- changeset sltx-p2:1
create unique index faculty_uniq
    on faculty (color, name);

