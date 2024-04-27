package com.example.demo.student.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.student.domain.Student;
import com.example.demo.student.dto.PlainResult;
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
    public PlainResult<List<Student>> getStudents() {
        return PlainResult.success(studentService.getStudents());
    }

    @PostMapping
    public PlainResult<Void> saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return PlainResult.success(null);
    }

    // api/v1/student/1
    @DeleteMapping(path = "{studentId}")
    public PlainResult<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return PlainResult.success(null);

    }

    // api/v1/student/studentId?name=a&email=76
    @PutMapping(path = "{studentId}")
    public PlainResult<Void> updateStudentById(@PathVariable Long studentId,
                                  @RequestParam String name,
                                  @RequestParam String email) {
        studentService.updateStudentById(studentId, name, email);
        return PlainResult.success(null);
    }


    @GetMapping("/page")
    public PlainResult<IPage<Student>> getStudentsByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        log.info("热部署测试 自动构建");
        return PlainResult.success(studentService.getStudentsByPage(pageNum, pageSize));
    }
}
