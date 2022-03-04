package com.challenge.metadata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.metadata.dtos.StudentDTO;
import com.challenge.metadata.model.Student;
import com.challenge.metadata.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(StudentController.URL)
public class StudentController {
	public static final String URL = "/student";

	@Autowired
	private StudentService studentService;

	@Operation(summary = "Get a student by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the student", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "student not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Student> find(@RequestParam Long id) {
		return ResponseEntity.ok(studentService.get(id));

	}

	@Operation(summary = "Get all student")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all the students", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "student not found", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Student>> findAll() {
		return ResponseEntity.ok(studentService.getAll());

	}

	@Operation(summary = "delete a student by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "delete the student", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "student not found", content = @Content) })
	@DeleteMapping("/remove/{id}")
	public BodyBuilder deletebyId(@RequestParam Long id) {
		studentService.delete(id);
		return ResponseEntity.ok();
	}

	@Operation(summary = "Update a student by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update info the student", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "student not found", content = @Content) })
	@PutMapping("/update")
	public ResponseEntity<Student> update(@RequestBody StudentDTO student) {
		return ResponseEntity.ok(studentService.update(student));
	}

	@Operation(summary = "Create a student by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created the student", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Student.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "student not found", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<Student> create(@RequestBody StudentDTO student) {
		return ResponseEntity.accepted().body(studentService.create(student));
	}

}
