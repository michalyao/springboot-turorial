package com.example.demo.student.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.student.domain.Student;
import com.example.demo.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    // api/v1/student/1
    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {

        studentService.deleteStudentById(studentId);
    }

    // api/v1/student/studentId?name=a&email=76
    @PutMapping(path = "{studentId}")
    public void updateStudentById(@PathVariable Long studentId,
                                  @RequestParam String name,
                                  @RequestParam String email) {
        studentService.updateStudentById(studentId, name, email);
    }


    @GetMapping("/page")
    public IPage<Student> getStudentsByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        log.info("热部署测试 自动构建");
        return studentService.getStudentsByPage(pageNum, pageSize);
    }
}
