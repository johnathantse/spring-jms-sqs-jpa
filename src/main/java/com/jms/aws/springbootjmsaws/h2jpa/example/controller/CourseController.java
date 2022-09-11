package com.jms.aws.springbootjmsaws.h2jpa.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;
import com.jms.aws.springbootjmsaws.h2jpa.example.service.CourseService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @RequestMapping(value = "/courses", method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);

    }

    @RequestMapping(value = "/courses", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
    }

}
