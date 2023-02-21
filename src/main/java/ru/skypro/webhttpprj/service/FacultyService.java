package ru.skypro.webhttpprj.service;


import org.springframework.stereotype.Service;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFaculty(long id, Faculty faculty) {
//        if (!facultyRepository.existsById(id)) {
//            return null;
//        }
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findAllByColorLikeAndNameLike(String color, String name) {
        return facultyRepository.findAllByColorLikeAndNameLike(color, name);
    }

    public Collection<Student> getStudents(int id) {
        return findFaculty(id).getStudents();
    }
}
