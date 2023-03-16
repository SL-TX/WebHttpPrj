package ru.skypro.webhttpprj.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.webhttpprj.model.Faculty;
import ru.skypro.webhttpprj.model.Student;
import ru.skypro.webhttpprj.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
@Tag(name = "Контроллер факультетов", description = "REST")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    @Operation(summary = "GET")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    @Operation(summary = "CREATE")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    @Operation(summary = "SET")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty.getId(),faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "DELETE")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "GET by color")
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("match")
    @Operation(summary = "GET by color and name")
    public ResponseEntity<Collection<Faculty>> findByColorAndName(@RequestParam(required = false) String color, @RequestParam String name) {
        if (name != null && !name.isBlank() && color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findAllByColorLikeAndNameLike(color,name));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("getStudents")
    @Operation(summary = "GET students of Faculty ")
    public Collection<Student> getStudents(@RequestParam int id){
        return facultyService.getStudents(id);
    }

    @GetMapping("getLongestFacultyName")
    public String getLongestFacultyName(){
        return facultyService.getLongestFacultyName();
    }
}