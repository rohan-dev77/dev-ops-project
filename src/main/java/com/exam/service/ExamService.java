package com.exam.service;

import com.exam.model.Arrear;
import com.exam.model.ExamRegistration;
import com.exam.model.Payment;
import com.exam.model.Subject;
import com.exam.repository.ArrearRepository;
import com.exam.repository.ExamRegistrationRepository;
import com.exam.repository.PaymentRepository;
import com.exam.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ArrearRepository arrearRepository;

    @Autowired
    private ExamRegistrationRepository examRegistrationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Fetch 8 random subjects for a semester
    public List<Subject> getRandomSubjectsForSemester(Integer semester) {
        return subjectRepository.findRandomSubjectsBySemester(semester);
    }

    // Fetch arrear subjects for a student
    public List<Arrear> getArrearsForStudent(Long studentId) {
        return arrearRepository.findByStudentId(studentId);
    }

    // Save Registration
    public ExamRegistration registerExams(ExamRegistration registration) {
        return examRegistrationRepository.save(registration);
    }

    // Get Registration by ID
    public ExamRegistration getRegistrationById(Long registrationId) {
        return examRegistrationRepository.findById(registrationId).orElse(null);
    }

    // Process Payment Simulation
    public Payment processPayment(Long registrationId, Long studentId, Double totalAmount) {
        ExamRegistration registration = examRegistrationRepository.findById(registrationId).orElse(null);
        if (registration != null) {
            registration.setPaymentStatus("COMPLETED");
            examRegistrationRepository.save(registration);
            
            Payment payment = new Payment(registrationId, studentId, totalAmount, generateTransactionId(), "SUCCESS");
            return paymentRepository.save(payment);
        }
        return null;
    }

    public Payment getPaymentConfirmation(Long registrationId) {
        return paymentRepository.findByRegistrationId(registrationId);
    }

    public void generateHallTicket(Long registrationId) {
        ExamRegistration registration = examRegistrationRepository.findById(registrationId).orElse(null);
        if (registration != null) {
            registration.setHallTicketGenerated(true);
            examRegistrationRepository.save(registration);
        }
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
    
    // Helper to get Subject details from comma separated string
    public List<Subject> getSubjectsByCodes(List<String> subjectCodes) {
        return subjectRepository.findAll().stream()
                .filter(s -> subjectCodes.contains(s.getSubjectCode()))
                .toList();
    }
}
