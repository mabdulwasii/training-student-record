package com.training.student.controller;

import com.training.student.entity.Student;
import com.training.student.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.training.student.exception.ErrorResponse.buildErrorResponse;
import static com.training.student.util.Validation.validateCreateStudentRequest;
import static com.training.student.util.Validation.validateUpdateStudentRequest;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:8080", "http://127.0.0.1:3000"}, methods = RequestMethod.GET)
@RequestMapping("/api/students")
public class StudentController {

    private static final Log logger = LogFactory.getLog(StudentController.class);

    //dependency injection
    //1. Annotation @Autowired
    //    @Autowired
    //    private StudentService service;

    //2. Constructor Injection
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //3. Field Injection


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
        logger.info("Request to get a student record with id : " + id);
        if (id < 1) {
            return ResponseEntity.badRequest().body(
                    buildErrorResponse("Id cannot be less than 1", BAD_REQUEST)
            );
        }

        Student student = service.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok().body(student);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<?> getStudentOptional(@PathVariable Integer id) {
        logger.info("Request to get a student record v2 with id : " + id);
        if (id < 1) {
            return ResponseEntity.badRequest().body(
                    buildErrorResponse("Id cannot be less than 1", BAD_REQUEST)
            );
        }

        Optional<Student> studentOptional = service.getStudentOptional(id);

//        if (studentOptional.isPresent()) {
//            return ResponseEntity.ok().body(studentOptional.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

        return studentOptional
                .map(student -> ResponseEntity.ok().body(student))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) throws URISyntaxException {
        if (student.getId() != null) {
            return validateCreateStudentRequest(student);
        }
        Student createdStudent = service.createStudent(student);
        return ResponseEntity.created(new URI("/api/students/v2/" + createdStudent.getId())).body(createdStudent);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) throws URISyntaxException {
        if (student.getId() == null) {
            return validateUpdateStudentRequest(student);
        }
        Optional<Student> updatedStudent = service.updateStudent(student);

        if (updatedStudent.isPresent()) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.badRequest().body(
                    buildErrorResponse("Student with Id " + student.getId() + " does not exist", BAD_REQUEST)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    /** Sample json

    {
    "name" : "David",
    "age" : 9,
    "addresses" : [
        {
            "address" : "2 ade road",
            "city": "ilorin",
            "state": "Kwara"
        },
          {
            "address" : "4 Sokoto road",
            "city": "ilorin",
            "state": "Kwara"
        }
    ]
}*/

}
