package com.challenge.metadata.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.challenge.metadata.model.AcademicClass;
import com.challenge.metadata.model.Course;
import com.challenge.metadata.model.Student;
import com.challenge.metadata.repositorys.AcademicClassRepository;
import com.challenge.metadata.repositorys.CourseRepository;
import com.challenge.metadata.repositorys.StudentRepository;

@RunWith(MockitoJUnitRunner.class)
public class AcademicClassServiceImplTest {
	@InjectMocks
	private AcademicClassServiceImpl academicClassService;
	@Mock
	private CourseRepository courseRepository;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private AcademicClassRepository academicClassRepository;

	@Test
	public final void testErnrollingStudentInCourse() throws Exception {
		Course course = mock(Course.class);

		when(courseRepository.getById(1l)).thenReturn(null);

		when(studentRepository.getById(1l)).thenReturn(null);

		Exception exception = assertThrows(Exception.class, () -> {
			academicClassService.ernrollingStudentInCourse(1l, 1l);
		});

		String expectedMessage = "course not found";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

		when(courseRepository.getById(2l)).thenReturn(course);

		when(studentRepository.getById(2l)).thenReturn(null);

		Exception exception2 = assertThrows(Exception.class, () -> {
			academicClassService.ernrollingStudentInCourse(2l, 2l);
		});

		String expectedMessage2 = "student not found";
		String actualMessage2 = exception2.getMessage();

		assertTrue(actualMessage2.contains(expectedMessage2));

		when(courseRepository.getById(3l)).thenReturn(course);
		when(studentRepository.getById(3l)).thenReturn(studentWith5Course());

		Exception exception3 = assertThrows(Exception.class, () -> {
			academicClassService.ernrollingStudentInCourse(3l, 3l);
		});

		String expectedMessage3 = "You have already exceeded the course limit";
		String actualMessage3 = exception3.getMessage();

		assertTrue(actualMessage3.contains(expectedMessage3));

		when(courseRepository.getById(4l)).thenReturn(course);
		when(studentRepository.getById(4l)).thenReturn(studentWith4Course());
		when(academicClassRepository.findByCourseId(4l)).thenReturn(academicClassFull());

		Exception exception4 = assertThrows(Exception.class, () -> {
			academicClassService.ernrollingStudentInCourse(4l, 4l);
		});

		String expectedMessage4 = "full class please have another course";
		String actualMessage4 = exception4.getMessage();

		assertTrue(actualMessage4.contains(expectedMessage4));

		when(courseRepository.getById(5l)).thenReturn(course);
		when(studentRepository.getById(5l)).thenReturn(studentWith4Course());
		when(academicClassRepository.findByCourseId(5l)).thenReturn(academicClassNotFull());
		assertTrue(academicClassService.ernrollingStudentInCourse(5l, 5l));

		when(courseRepository.getById(6l)).thenReturn(course);
		when(studentRepository.getById(6l)).thenReturn(studentWith4Course());
		when(academicClassRepository.findByCourseId(6l)).thenReturn(null);
		assertTrue(academicClassService.ernrollingStudentInCourse(6l, 6l));
		
		
	}

	@Test
	public final void testStudentPerCourse() {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCoursePerStudent() {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testStudentsWithoutAnyCourses() {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCoursesWithoutAnyStudents() {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	private Student studentWith5Course() {
		Student student = new Student();
		student.setAcademicClass(list5AcademicClass());
		return student;
	}

	private Student studentWith4Course() {
		Student student = new Student();
		student.setAcademicClass(list4AcademicClass());
		return student;
	}

	private List<AcademicClass> list5AcademicClass() {
		AcademicClass academicClass = mock(AcademicClass.class);
		List<AcademicClass> academicClasses = new ArrayList<AcademicClass>();
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);

		return academicClasses;
	}

	private List<AcademicClass> list4AcademicClass() {
		AcademicClass academicClass = mock(AcademicClass.class);
		List<AcademicClass> academicClasses = new ArrayList<AcademicClass>();
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);
		academicClasses.add(academicClass);

		return academicClasses;
	}

	private AcademicClass academicClassFull() {
		AcademicClass academicClass = new AcademicClass();
		List<Student> list = new ArrayList<Student>();
		for (int i = 0; i <= 49; i++) {
			list.add(studentWith4Course());

		}
		academicClass.setRegistered(list);
		return academicClass;
	}

	private AcademicClass academicClassNotFull() {
		AcademicClass academicClass = new AcademicClass();
		List<Student> list = new ArrayList<Student>();
		for (int i = 0; i <= 40; i++) {
			list.add(studentWith4Course());

		}
		academicClass.setRegistered(list);
		return academicClass;
	}

}
