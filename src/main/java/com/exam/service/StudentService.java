package com.exam.service;

import com.exam.model.Student;
import com.exam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student authenticateStudent(String username, String password) {
        Student student = studentRepository.findByUsername(username);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }
    
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
