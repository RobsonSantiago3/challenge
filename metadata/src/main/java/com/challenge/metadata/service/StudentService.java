package com.challenge.metadata.service;

import java.util.List;

import com.challenge.metadata.dtos.StudentDTO;
import com.challenge.metadata.model.Student;

public interface StudentService {

	Student create(StudentDTO studentDTO);

	Student update(StudentDTO studentDTO);

	void delete(Long id);

	Student get(Long di);

	List<Student> getAll();

}
