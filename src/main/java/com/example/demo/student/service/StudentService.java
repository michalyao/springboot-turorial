package com.example.demo.student.service;

import com.example.demo.student.domain.Student;

import java.util.List;

public interface StudentService {
    /**
     * 学生信息列表查询
     * @return
     */
    List<Student> getStudents();

    /**
     * 保存学生信息
     * @param student
     */
    void saveStudent(Student student);

    /**
     * 删除学生信息
     * @param studentId
     */
    void deleteStudentById(Long studentId);


    /**
     * 根据 studentId 来更新学生的姓名和邮箱
     * @param studentId
     * @param name
     * @param email
     */
    void updateStudentById(Long studentId, String name, String email);
}

