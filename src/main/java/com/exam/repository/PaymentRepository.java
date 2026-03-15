package com.exam.repository;

import com.exam.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByRegistrationId(Long registrationId);
}
