package com.challenge.metadata.controller;

import java.util.List;

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

import com.challenge.metadata.model.Course;
import com.challenge.metadata.repositorys.CourseRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(CourseController.URL)
public class CourseController {
	public static final String URL = "/course";

	private CourseRepository repository;

	@Operation(summary = "Get a Course by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Course", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Course.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Course not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Course> find(@RequestParam Long id) {
		return ResponseEntity.ok(repository.getById(id));

	}

	@Operation(summary = "Get all Course")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found all the Courses", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Course not found", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Course>> findAll() {
		return ResponseEntity.ok(repository.findAll());

	}

	@Operation(summary = "delete a Course by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "delete the Course", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Course.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Course not found", content = @Content) })
	@DeleteMapping("/remove/{id}")
	public BodyBuilder deletebyId(@RequestParam Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok();
	}

	@Operation(summary = "Update a Course by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update info the Course", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Course.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Course not found", content = @Content) })
	@PutMapping("/update/{id}")
	public ResponseEntity<Course> update(@RequestBody Course course) {
		return ResponseEntity.ok(repository.save(course));
	}

	@Operation(summary = "Create a Course by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created the Course", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Course.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Course not found", content = @Content) })
	@PostMapping("/create/{id}")
	public ResponseEntity<Course> create(@RequestBody Course course) {
		return ResponseEntity.accepted().body(repository.save(course));
	}

}
