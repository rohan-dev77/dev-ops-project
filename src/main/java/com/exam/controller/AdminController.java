package com.exam.controller;

import com.exam.model.Admin;
import com.exam.model.Student;
import com.exam.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String adminLoginSubmit(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Admin admin = adminService.authenticateAdmin(username, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Invalid Admin Credentials");
        return "admin-login";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        model.addAttribute("students", adminService.getAllStudents());
        return "admin-dashboard";
    }

    @GetMapping("/add-student")
    public String addStudentPage(HttpSession session, Model model) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/add-student")
    public String addStudentSubmit(@ModelAttribute Student student, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        adminService.addStudent(student);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        adminService.removeStudent(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
