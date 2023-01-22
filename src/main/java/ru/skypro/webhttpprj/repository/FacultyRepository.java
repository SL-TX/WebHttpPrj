package ru.skypro.webhttpprj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.webhttpprj.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);
}