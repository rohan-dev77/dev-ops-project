package com.exam.repository;

import com.exam.model.Arrear;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArrearRepository extends JpaRepository<Arrear, Long> {
    List<Arrear> findByStudentId(Long studentId);
}
