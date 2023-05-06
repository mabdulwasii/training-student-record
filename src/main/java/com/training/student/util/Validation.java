package com.training.student.util;

import com.training.student.entity.Student;
import com.training.student.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.training.student.exception.ErrorResponse.*;

public class Validation {

    private Validation() {}

    public static ResponseEntity<?> validateCreateStudentRequest(Student student) {
//        ErrorResponse error = new ErrorResponse()
//                .setDescription("Id should be null, id = " + student.getId())
//                .setErrorCode(HttpStatus.BAD_REQUEST.value())
//                .setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Id should be null, id = " + student.getId(),
                        HttpStatus.BAD_REQUEST
                )
        );
    }

    public static ResponseEntity<?> validateUpdateStudentRequest(Student student) {
        return ResponseEntity.badRequest().body(
                buildErrorResponse("Id cannot be null, id = " + student.getId(),
                        HttpStatus.BAD_REQUEST
                )
        );
    }
}
