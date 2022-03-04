package com.challenge.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.metadata.dtos.CourseDTO;
import com.challenge.metadata.model.Course;
import com.challenge.metadata.repositorys.CourseRepository;
import com.challenge.metadata.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course create(CourseDTO courseDTO) {
		Course course = new Course();
		course.setName(courseDTO.getName());

		return courseRepository.save(course);
	}

	@Override
	public Course update(CourseDTO courseDTO) {
		Course course = new Course();
		course.setName(courseDTO.getName());
		course.setId(courseDTO.getId());

		return courseRepository.save(course);
	}

	@Override
	public void delete(Long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public Course get(Long di) {
		return courseRepository.getById(di);
	}

	@Override
	public List<Course> getAll() {
		return courseRepository.findAll();
	}

}
