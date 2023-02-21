select s.name, s.age, f.name from student s left join faculty f on s.faculty_id = f.id;

select s.* from avatar a left join student s on s.id = a.student_id