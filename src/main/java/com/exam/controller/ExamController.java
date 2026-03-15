package com.exam.controller;

import com.exam.model.ExamRegistration;
import com.exam.model.Student;
import com.exam.model.Subject;
import com.exam.service.ExamService;
import com.exam.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExamController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Student student = studentService.authenticateStudent(username, password);
        if (student != null) {
            session.setAttribute("student", student);
            return "redirect:/semester";
        }
        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }

    @GetMapping("/semester")
    public String semesterPage(HttpSession session, Model model) {
        if (session.getAttribute("student") == null) return "redirect:/login";
        return "semester";
    }

    @PostMapping("/semester")
    public String semesterSubmit(@RequestParam Integer semester, HttpSession session) {
        if (session.getAttribute("student") == null) return "redirect:/login";
        session.setAttribute("selectedSemester", semester);
        return "redirect:/registration";
    }

    @GetMapping("/registration")
    public String registrationPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        Integer semester = (Integer) session.getAttribute("selectedSemester");

        if (student == null || semester == null) return "redirect:/login";

        model.addAttribute("randomSubjects", examService.getRandomSubjectsForSemester(semester));
        model.addAttribute("arrearSubjects", examService.getArrearsForStudent(student.getId()));
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@RequestParam(required = false) List<String> subjects, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        Integer semester = (Integer) session.getAttribute("selectedSemester");

        if (student == null || semester == null) return "redirect:/login";

        if (subjects == null || subjects.isEmpty()) {
            model.addAttribute("error", "Please select at least one subject.");
            model.addAttribute("randomSubjects", examService.getRandomSubjectsForSemester(semester));
            model.addAttribute("arrearSubjects", examService.getArrearsForStudent(student.getId()));
            return "registration";
        }

        if (subjects.size() > 10) {
            model.addAttribute("error", "Maximum exam limit exceeded. You can write only 10 exams per semester.");
            model.addAttribute("randomSubjects", examService.getRandomSubjectsForSemester(semester));
            model.addAttribute("arrearSubjects", examService.getArrearsForStudent(student.getId()));
            return "registration";
        }

        String subjectsStr = String.join(",", subjects);
        ExamRegistration reg = new ExamRegistration(student.getId(), semester, subjectsStr, subjects.size(), "PENDING", false);
        ExamRegistration savedReg = examService.registerExams(reg);
        
        session.setAttribute("registrationId", savedReg.getRegistrationId());
        return "redirect:/payment";
    }

    @GetMapping("/payment")
    public String paymentPage(HttpSession session, Model model) {
        Long regId = (Long) session.getAttribute("registrationId");
        if (regId == null) return "redirect:/login";

        ExamRegistration reg = examService.getRegistrationById(regId);
        int totalSubjects = reg.getTotalSubjects();
        double totalFee = totalSubjects * 150.0;

        model.addAttribute("totalSubjects", totalSubjects);
        model.addAttribute("totalFee", totalFee);
        return "payment";
    }

    @PostMapping("/payment")
    public String paymentSubmit(HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        Long regId = (Long) session.getAttribute("registrationId");
        if (student == null || regId == null) return "redirect:/login";

        ExamRegistration reg = examService.getRegistrationById(regId);
        double totalFee = reg.getTotalSubjects() * 150.0;

        examService.processPayment(regId, student.getId(), totalFee);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmationPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        Long regId = (Long) session.getAttribute("registrationId");
        if (student == null || regId == null) return "redirect:/login";

        ExamRegistration reg = examService.getRegistrationById(regId);
        model.addAttribute("student", student);
        model.addAttribute("registration", reg);
        model.addAttribute("payment", examService.getPaymentConfirmation(regId));
        
        // Load actual subject names instead of just codes
        List<String> subjectCodes = Arrays.asList(reg.getSubjectsSelected().split(","));
        model.addAttribute("selectedSubjects", examService.getSubjectsByCodes(subjectCodes));

        return "confirmation";
    }

    @GetMapping("/hallticket")
    public String hallTicketPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        Long regId = (Long) session.getAttribute("registrationId");
        if (student == null || regId == null) return "redirect:/login";

        examService.generateHallTicket(regId);
        ExamRegistration reg = examService.getRegistrationById(regId);
        
        model.addAttribute("student", student);
        model.addAttribute("registration", reg);
        
        List<String> subjectCodes = Arrays.asList(reg.getSubjectsSelected().split(","));
        model.addAttribute("selectedSubjects", examService.getSubjectsByCodes(subjectCodes));
        return "hallticket";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
