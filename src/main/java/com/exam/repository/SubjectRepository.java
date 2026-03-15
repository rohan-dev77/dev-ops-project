package com.exam.repository;

import com.exam.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    @Query(value = "SELECT * FROM subject WHERE semester = :semester ORDER BY RAND() LIMIT 8", nativeQuery = true)
    List<Subject> findRandomSubjectsBySemester(@Param("semester") Integer semester);
}
