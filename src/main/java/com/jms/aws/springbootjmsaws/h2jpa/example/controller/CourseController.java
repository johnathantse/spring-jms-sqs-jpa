package com.jms.aws.springbootjmsaws.h2jpa.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.CourseService;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.HttpsResponseService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private HttpsResponseService responseService;

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Course course = courseService.getCourseById(id);
        return responseService.handleRequest(course);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return responseService.handleRequest(courses);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return responseService.handleRequest(newCourse);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.PUT)
    public ResponseEntity<Optional<Course>> updateCourse(@RequestBody Course course) {
        Optional<Course> updatedCourse = courseService.updateCourse(course);
        return responseService.handleRequest(updatedCourse);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.DELETE)
    public ResponseEntity<Optional<Course>> deleteCourse(@RequestBody Course course) {
        Optional<Course> deletedCourse = courseService.deleteCourse(course);
        return responseService.handleRequest(deletedCourse);

    }

}
