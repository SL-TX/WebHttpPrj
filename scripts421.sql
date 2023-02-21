ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 15);

ALTER TABLE student
    ADD CONSTRAINT name_uniq UNIQUE (name);

ALTER TABLE student
    ADD CONSTRAINT name_not_null check ( name is not null and name != '0' and name != '' );

alter table faculty
    add constraint faculty_uniq
        unique (color, name);

alter table student
    alter column age set default 20;