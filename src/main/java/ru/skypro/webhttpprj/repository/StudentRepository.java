package ru.skypro.webhttpprj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.webhttpprj.dto.StudentDto;
import ru.skypro.webhttpprj.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    @Query(value = "SELECT count(*) from student s", nativeQuery = true)
    Integer countStudent();

    @Query(value = "SELECT avg(age) from student s", nativeQuery = true)
    Integer avgAge();

    @Query(value = "SELECT s.id, s.name, s.age from student s order by s.id desc limit 5", nativeQuery = true)
    List<StudentDto> lastStudents();
}