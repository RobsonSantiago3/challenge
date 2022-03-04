package com.challenge.metadata.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.challenge.metadata.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("SELECT a from Student as a where a.academicClass is null")
	List<Student> findByIsNullAcademicClass();

}
