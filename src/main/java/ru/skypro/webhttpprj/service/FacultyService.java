package ru.skypro.webhttpprj.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for findFaculty");
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFaculty(long id, Faculty faculty) {
        logger.info("Was invoked method for editFaculty");
//        if (!facultyRepository.existsById(id)) {
//            return null;
//        }
        facultyRepository.save(faculty);
        return faculty;
    }

    public void deleteFaculty(long id) {
        logger.warn("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Was invoked method for findByColor");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findAllByColorLikeAndNameLike(String color, String name) {
        logger.info("Was invoked method for findAllByColorLikeAndNameLike");
        return facultyRepository.findAllByColorLikeAndNameLike(color, name);
    }

    public Collection<Student> getStudents(int id) {
        logger.info("Was invoked method for getStudents");
        return findFaculty(id).getStudents();
    }
}
