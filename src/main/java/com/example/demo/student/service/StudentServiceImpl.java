package com.example.demo.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.student.dal.StudentMapper;
import com.example.demo.student.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudents() {
        return studentMapper.selectList(null);
    }

    @Override
    public void saveStudent(Student student) {
        Student select = studentMapper.selectByEmail(student.getEmail());
        if (select != null) {
            throw new IllegalStateException("email 已存在");
        }
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentMapper.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudentById(Long studentId, String name, String email) {
        Student select = studentMapper.selectById(studentId);
        if (select == null) {
            throw new IllegalStateException("学生 id 不存在");
        }
        if (name != null && !name.isEmpty()
                && !Objects.equals(name, select.getName())) {
            select.setName(name);
        }
        if (email != null && !email.isEmpty()
                && !Objects.equals(email, select.getEmail())) {
            select.setEmail(email);
        }
        studentMapper.updateById(select);
    }

    @Override
    public IPage<Student> getStudentsByPage(Integer pageNum, Integer pageSize) {
        return studentMapper.selectPage(new Page<>(pageNum, pageSize), null);
    }
}
