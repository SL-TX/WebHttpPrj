package ru.skypro.webhttpprj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.webhttpprj.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
}