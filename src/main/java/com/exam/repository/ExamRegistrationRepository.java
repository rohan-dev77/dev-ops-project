package com.exam.repository;

import com.exam.model.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration, Long> {
    List<ExamRegistration> findByStudentIdAndSemester(Long studentId, Integer semester);
    List<ExamRegistration> findByStudentId(Long studentId);
}
