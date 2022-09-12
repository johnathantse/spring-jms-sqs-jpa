package com.jms.aws.springbootjmsaws.h2jpa.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;
import com.jms.aws.springbootjmsaws.h2jpa.example.models.CourseNameRequest;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.CourseService;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.HttpsResponseService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CourseController {

    public static final String COURSE_ENDPOINT = "/courses";
    public static final String COURSE_NAME_ENDPONT = COURSE_ENDPOINT + "/course-name";

    @Autowired
    private CourseService courseService;

    @Autowired
    private HttpsResponseService responseService;

    @RequestMapping(value = COURSE_ENDPOINT + "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Course course = courseService.getCourseById(id);
        return responseService.handleRequest(course);
    }

    @RequestMapping(value = COURSE_ENDPOINT, method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return responseService.handleRequest(courses);
    }

    @RequestMapping(value = COURSE_ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return responseService.handleRequest(newCourse);
    }

    @RequestMapping(value = COURSE_ENDPOINT, method = RequestMethod.PUT)
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(course);
        return responseService.handleRequest(updatedCourse);
    }

    @RequestMapping(value = COURSE_ENDPOINT, method = RequestMethod.DELETE)
    public ResponseEntity<Course> deleteCourse(@RequestBody Course course) {
        Course deletedCourse = courseService.deleteCourse(course);
        return responseService.handleRequest(deletedCourse);
    }

    @RequestMapping(value = COURSE_NAME_ENDPONT, method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseByName(@RequestBody String courseName) {
        Course course = courseService.getCourseByName(courseName);
        return responseService.handleRequest(course);
    }

    @RequestMapping(value = COURSE_NAME_ENDPONT, method = RequestMethod.DELETE)
    public ResponseEntity<Course> deleteCourseByName(@RequestBody String courseName) {
        Course course = courseService.deleteCourseByName(courseName);
        return responseService.handleRequest(course);
    }

    @RequestMapping(value = COURSE_NAME_ENDPONT, method = RequestMethod.PUT)
    public ResponseEntity<Course> updateCourseName(@RequestBody CourseNameRequest courseNameUpdateRequest) {
        Course course = courseService.updateCourseByName(courseNameUpdateRequest.getOriginalName(),
                courseNameUpdateRequest.getNewName());
        return responseService.handleRequest(course);
    }

}
