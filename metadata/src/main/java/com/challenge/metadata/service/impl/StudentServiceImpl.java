package com.challenge.metadata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.metadata.dtos.StudentDTO;
import com.challenge.metadata.model.Student;
import com.challenge.metadata.repositorys.StudentRepository;
import com.challenge.metadata.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repository;

	@Override
	public Student create(StudentDTO studentDTO) {
		Student student = new Student();
		student.setEmail(studentDTO.getEmail());
		student.setName(studentDTO.getName());

		return repository.save(student);
	}

	@Override
	public Student update(StudentDTO studentDTO) {
		Student student = new Student();
		student.setEmail(studentDTO.getEmail());
		student.setName(studentDTO.getName());
		student.setId(studentDTO.getId());

		return repository.save(student);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Student get(Long di) {
		return repository.getById(di);
	}

	@Override
	public List<Student> getAll() {
		return repository.findAll();
	}

}
