package ru.skypro.webhttpprj.service;



import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.webhttpprj.dto.StudentDto;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student addStudent(Student student) {
        logger.info("Was invoked method for addStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for findStudent");
        return studentRepository.findById (id).orElseThrow();
    }

    public Student editStudent(long id, Student student) {
        logger.info("Was invoked method for editStudent");
//        if (!studentRepository.existsById(id)) {
//            return null;
//        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.warn("Was invoked method for deleteStudent");
        studentRepository.deleteById(id);
    }


    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getFaculty(int id) {
        logger.info("Was invoked method for getFaculty");
        return findStudent(id).getFaculty();
    }

    public Integer getCount() {
        logger.debug("Was invoked method for getCount");
        return studentRepository.countStudent();
    }

    public Integer getAvgAge() {
        logger.info("Was invoked method for getAvgAge");
        return studentRepository.avgAge();
    }

    public Collection<StudentDto> getLastStudents() {
        logger.info("Was invoked method for getLastStudents");
        return studentRepository.lastStudents();
    }

    public Collection<String> getStudentsStartWithA() {
        logger.info("Was invoked method for getStudentsStartWithA");
        var students = studentRepository.findAll();
        return students
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s->s.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getStudentsAverageAge() {
        logger.info("Was invoked method for getStudentsAverageAge");
        var students = studentRepository.findAll();
        return students
                .stream()
                .mapToInt(Student::getAge)
                .average().orElseThrow(
                        ()-> {
                            logger.error("Students age is not aviable");
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "err");
                        }
                );
    }

    public Collection<String> getAsyncStudentNames() {
        logger.info("Was invoked method for getAsyncStudentNames");
        var students = studentRepository.findAll(Sort.by("id"));
        var arr = new ArrayList<String>();
        if (students.size()>=6){
//            arr.add(students.get(0).getName());
//            arr.add(students.get(1).getName());
            System.out.println(students.get(0).getName());
            System.out.println(students.get(1).getName());
            new Thread(()->{
//                arr.add(students.get(2).getName());
//                arr.add(students.get(3).getName());
                System.out.println(students.get(2).getName());
                System.out.println(students.get(3).getName());
            }).start();
            new Thread(()->{
//                arr.add(students.get(4).getName());
//                arr.add(students.get(5).getName());
                System.out.println(students.get(4).getName());
                System.out.println(students.get(5).getName());
            }).start();
        }
        return arr;
    }

    public Collection<String> getSyncStudentNames() {
        logger.info("Was invoked method for getSyncStudentNames");
        var students = studentRepository.findAll(Sort.by("id"));
        var arr = new ArrayList<String>();
        if (students.size()>=6){
            addInArray(arr,students.get(0).getName());
            addInArray(arr,students.get(1).getName());
            new Thread(()->{
                addInArray(arr,students.get(2).getName());
                addInArray(arr,students.get(3).getName());
            }).start();
            new Thread(()->{
                addInArray(arr,students.get(4).getName());
                addInArray(arr,students.get(5).getName());
            }).start();
        }
        return arr;
    }

    private final Object flag = new Object();
    private void addInArray(ArrayList<String> arr, String name){
        synchronized (flag) {
//            arr.add(name);
            System.out.println(name);
        }
    }
}