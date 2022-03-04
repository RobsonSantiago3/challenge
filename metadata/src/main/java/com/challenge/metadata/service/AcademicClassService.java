package com.challenge.metadata.service;

import java.util.List;

import com.challenge.metadata.model.Course;
import com.challenge.metadata.model.Student;

public interface AcademicClassService {

	boolean ernrollingStudentInCourse(Long idStudent, Long idCourse) throws Exception;

	List<Course> studentPerCourse(Long idStudent);

	List<Student> coursePerStudent(Long idCourse);

	List<Student> studentsWithoutAnyCourses();

	List<Course> coursesWithoutAnyStudents();

}
