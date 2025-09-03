package com.wakeb.junittestappspring;

import com.wakeb.junittestappspring.repository.StudentRepository;
import com.wakeb.junittestappspring.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitTestAppSpringApplicationTests {

    // Field injection is not recommended, but used here for simplicity in the test context

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

    @Test
    void contextLoads() {
    }

}
