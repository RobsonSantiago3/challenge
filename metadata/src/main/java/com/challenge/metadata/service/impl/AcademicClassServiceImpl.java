package com.challenge.metadata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.metadata.model.AcademicClass;
import com.challenge.metadata.model.Course;
import com.challenge.metadata.model.Student;
import com.challenge.metadata.repositorys.AcademicClassRepository;
import com.challenge.metadata.repositorys.CourseRepository;
import com.challenge.metadata.repositorys.StudentRepository;
import com.challenge.metadata.service.AcademicClassService;

@Service
public class AcademicClassServiceImpl implements AcademicClassService {

	private static final int limitOfCoursesPerStudent = 5;

	private static final int studentLimitPerCourse = 50;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AcademicClassRepository academicClassRepository;

	@Override
	public boolean ernrollingStudentInCourse(Long idStudent, Long idCourse) throws Exception {
		Course course = courseRepository.getById(idCourse);

		if (Objects.isNull(course)) {
			throw new Exception("course not found");
		}

		Student student = studentRepository.getById(idStudent);

		if (Objects.isNull(student)) {
			throw new Exception("student not found");
		}

		long countCourseStudent = student.getAcademicClass().stream().count();

		if (countCourseStudent == limitOfCoursesPerStudent) {
			throw new Exception("You have already exceeded the course limit");
		}

		AcademicClass academicClass = academicClassRepository.findByCourseId(idCourse);

		if (Objects.nonNull(academicClass)) {
			long count = academicClass.getRegistered().stream().count();
			if (count == studentLimitPerCourse) {
				throw new Exception("full class please have another course");
			}
			academicClass.getRegistered().add(student);
			academicClassRepository.save(academicClass);
			return true;
		}
		academicClass = new AcademicClass();
		academicClass.setCourse(course);
		List<Student> students = new ArrayList<>();
		students.add(student);
		academicClass.setRegistered(students);
		academicClassRepository.save(academicClass);
		course.getCourses().add(academicClass);
		courseRepository.save(course);
		student.getAcademicClass().add(academicClass);
		studentRepository.save(student);
		return true;
	}

	@Override
	public List<Course> studentPerCourse(Long idStudent) {
		Student student = studentRepository.getById(idStudent);
		return student.getAcademicClass().stream().map(c -> c.getCourse()).collect(Collectors.toList());
	}

	@Override
	public List<Student> coursePerStudent(Long idCourse) {
		AcademicClass academicClass = academicClassRepository.findByCourseId(idCourse);
		return academicClass.getRegistered();
	}

	@Override
	public List<Student> studentsWithoutAnyCourses() {

		return studentRepository.findByIsNullAcademicClass();
	}

	@Override
	public List<Course> coursesWithoutAnyStudents() {
		return courseRepository.findByIsNullAcademicClass();
	}

}
