package com.jms.aws.springbootjmsaws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.test.context.junit4.SpringRunner;

import com.jms.aws.springbootjmsaws.h2jpa.example.dao.CourseRepo;
import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.CourseService;
import com.jms.aws.springbootjmsaws.sqs.SqsProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepo courseRepoMock;

    @MockBean
    private SimpleMessageListenerContainer sml;

    @MockBean
    private SqsProducer producer;

    private static List<Course> courses = new ArrayList<>();

    @BeforeEach
    void setup() {
        courses.add(new Course(1, "Java"));
        courses.add(new Course(2, "SpringBoot"));
        courses.add(new Course(3, "AWS"));

        when(courseRepoMock.findAll()).thenReturn(courses);
    }

    private void mockSave(Course course) {
        when(courseRepoMock.save(course)).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void addCourseTest() {
        Course newCourse = new Course(4, "Python");
        // when(courseRepoMock.save(newCourse)).thenAnswer(i -> i.getArguments()[0]);
        mockSave(newCourse);
        Course course = courseService.addCourse(newCourse);
        assertEquals("Python", course.getCourseName());
    }

    @Test
    void getCourseById() {
        when(courseRepoMock.findById(0)).thenReturn(Optional.of(courses.get(0)));
        Course course = courseService.getCourseById(0);
        assertEquals("Java", course.getCourseName());
    }

    @Test
    void getCourseByName() {
        String originalCourseName = "AWS";
        when(courseRepoMock.findByCourseName(originalCourseName)).thenReturn(Optional.of(courses.get(2)));
        Course course = courseService.getCourseByName("AWS");
        assertEquals("AWS", course.getCourseName());
    }

    @Test
    void updateCourseByName() {
        String originalCourseName = "AWS";
        String updatedCourseName = "Amazon Web Services";
        when(courseRepoMock.findByCourseName(originalCourseName)).thenReturn(Optional.of(courses.get(2)));
        mockSave(courses.get(2));
        Course course = courseService.updateCourseByName(originalCourseName, updatedCourseName);
        assertEquals("Amazon Web Services", course.getCourseName());
    }

    @Test
    public void getCoursesTest() {
        List<Course> courses = courseService.getAllCourses();
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course(1, "Java"));
        expectedCourses.add(new Course(2, "SpringBoot"));
        expectedCourses.add(new Course(3, "AWS"));
        int idx = 0;
        for (Course course : courses) {
            Course expectedCourse = expectedCourses.get(idx);
            assertEquals(course.getCourseName(), expectedCourse.getCourseName());
            idx++;
        }
    }

}
