package ru.skypro.webhttpprj.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.webhttpprj.dto.StudentDto;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
@Tag(name = "Контроллер студентов", description = "REST")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    @Operation(summary = "GET")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    @Operation(summary = "CREATE")
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    @Operation(summary = "SET")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student.getId(), student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "DELETE")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "GET by age")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("between")
    @Operation(summary = "GET between ages")
    public ResponseEntity<Collection<Student>> findBetween(@RequestParam(required = false) int min,@RequestParam int max) {
        if (min > 0 && max > 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min,max));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("getFaculty")
    @Operation(summary = "GET Faculty of student")
    public ResponseEntity<Faculty> getFaculty(@RequestParam int id){
        return ResponseEntity.ok(studentService.getFaculty(id));
    }

    @GetMapping("getCount")
    public Integer getCount(){
        return studentService.getCount();
    }

    @GetMapping("getAvgAge")
    public Integer getAvgAge(){
        return studentService.getAvgAge();
    }
    @GetMapping("getLastStudents")
    public Collection<StudentDto> getLastStudents(){
        return studentService.getLastStudents();
    }
}