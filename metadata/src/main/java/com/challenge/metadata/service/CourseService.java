package com.challenge.metadata.service;

import java.util.List;

import com.challenge.metadata.dtos.CourseDTO;
import com.challenge.metadata.model.Course;

public interface CourseService {

	Course create(CourseDTO courseDTO);

	Course update(CourseDTO courseDTO);

	void delete(Long id);

	Course get(Long di);

	List<Course> getAll();

}
