package com.wakeb.junittestappspring;

import com.wakeb.junittestappspring.entity.Student;
import com.wakeb.junittestappspring.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    // Inject the random port used in the test
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate rest;

    @Autowired
    StudentRepository repo;

    @BeforeEach
    void seed() {
        // clear and save some data in the db
        repo.deleteAll();
        repo.saveAll(List.of(
                new Student(null, "Majed"),
                new Student(null, "Ahmed")
        ));
    }

    @Test
    void getAllStudents_returnsDataFromDb() {
        String url = "http://localhost:"+port+"/api/all";
        ResponseEntity<Student[]> resp = rest.getForEntity(url, Student[].class);

        // status: to check if the response status is 200 OK
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);


        // type: to check if the response content type is JSON
        assertThat(resp.getHeaders().getContentType()).satisfies(ct ->
                assertThat(ct).isNotNull().matches(MediaType.APPLICATION_JSON::includes));

        // content: to check if the response body contains expected data
        Student[] body = resp.getBody();
        assertThat(body).isNotNull();
        assertThat(body.length).isGreaterThan(0);
        assertThat(body[0].getStudentName()).isIn("Majed", "Ahmed");
    }
}
