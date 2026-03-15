package com.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Arrear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String subjectCode;
    private String subjectName;
    private Integer semesterFailed;

    public Arrear() {
    }

    public Arrear(Long studentId, String subjectCode, String subjectName, Integer semesterFailed) {
        this.studentId = studentId;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.semesterFailed = semesterFailed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSemesterFailed() {
        return semesterFailed;
    }

    public void setSemesterFailed(Integer semesterFailed) {
        this.semesterFailed = semesterFailed;
    }
}
