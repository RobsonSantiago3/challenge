package com.challenge.metadata.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.metadata.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Query("SELECT a from Course as a where a.courses is null")
	List<Course> findByIsNullAcademicClass();

}
