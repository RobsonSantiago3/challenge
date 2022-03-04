package com.challenge.metadata.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.metadata.model.AcademicClass;

@Repository
public interface AcademicClassRepository extends JpaRepository<AcademicClass, Long> {

	@Query("SELECT a from AcademicClass as a where a.course.id = :courseId")
	AcademicClass findByCourseId(Long courseId);

}
