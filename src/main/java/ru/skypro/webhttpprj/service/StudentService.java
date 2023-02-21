package ru.skypro.webhttpprj.service;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.repository.StudentRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById (id).orElseThrow();
    }

    public Student editStudent(long id, Student student) {
//        if (!studentRepository.existsById(id)) {
//            return null;
//        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }


    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getFaculty(int id) {
        return findStudent(id).getFaculty();
    }
}