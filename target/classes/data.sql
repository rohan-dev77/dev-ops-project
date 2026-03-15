-- Insert Admin
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

-- Insert Students
INSERT INTO student (username, password, register_no, name, department, current_semester) 
VALUES ('johndoe', 'password123', 'REG001', 'John Doe', 'Computer Science', 4);

INSERT INTO student (username, password, register_no, name, department, current_semester) 
VALUES ('janedoe', 'password123', 'REG002', 'Jane Doe', 'Information Technology', 4);

-- Insert Subjects for Semester 4 (Let's add around 12 to test randomization limit 8)
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS401', 'Artificial Intelligence');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS402', 'Computer Networks');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS403', 'Operating Systems');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS404', 'Machine Learning');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS405', 'Compiler Design');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS406', 'Software Engineering');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS407', 'Distributed Systems');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS408', 'Cloud Computing');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS409', 'Database Management');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS410', 'Data Structures');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS411', 'Web Development');
INSERT INTO subject (semester, subject_code, subject_name) VALUES (4, 'CS412', 'Cryptography');

-- Insert Arrears for John Doe (Student ID 1 needs some arrears)
INSERT INTO arrear (student_id, subject_code, subject_name, semester_failed) 
VALUES (1, 'MA201', 'Mathematics II', 2);
INSERT INTO arrear (student_id, subject_code, subject_name, semester_failed) 
VALUES (1, 'PH101', 'Engineering Physics', 1);
INSERT INTO arrear (student_id, subject_code, subject_name, semester_failed) 
VALUES (1, 'CS201', 'Digital Logic', 2);
