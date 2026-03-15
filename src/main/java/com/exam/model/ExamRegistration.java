package com.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    private Long studentId;
    private Integer semester;
    private String subjectsSelected; // Comma-separated subject codes
    private Integer totalSubjects;
    private String paymentStatus; // PENDING, COMPLETED
    private Boolean hallTicketGenerated;

    public ExamRegistration() {
    }

    public ExamRegistration(Long studentId, Integer semester, String subjectsSelected, Integer totalSubjects, String paymentStatus, Boolean hallTicketGenerated) {
        this.studentId = studentId;
        this.semester = semester;
        this.subjectsSelected = subjectsSelected;
        this.totalSubjects = totalSubjects;
        this.paymentStatus = paymentStatus;
        this.hallTicketGenerated = hallTicketGenerated;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getSubjectsSelected() {
        return subjectsSelected;
    }

    public void setSubjectsSelected(String subjectsSelected) {
        this.subjectsSelected = subjectsSelected;
    }

    public Integer getTotalSubjects() {
        return totalSubjects;
    }

    public void setTotalSubjects(Integer totalSubjects) {
        this.totalSubjects = totalSubjects;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getHallTicketGenerated() {
        return hallTicketGenerated;
    }

    public void setHallTicketGenerated(Boolean hallTicketGenerated) {
        this.hallTicketGenerated = hallTicketGenerated;
    }
}
