package com.jms.aws.springbootjmsaws.h2jpa.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jms.aws.springbootjmsaws.h2jpa.example.dao.CourseRepo;
import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepo.findAll().forEach(courses::add);
        return courses;
    }

    public Course getCourseById(Integer id) {
        return courseRepo.findById(id).orElse(null);
    }

    public void updateCourse(Course course) {
        courseRepo.save(course);
    }

    public void deletecourse(Integer id) {
        courseRepo.deleteById(id);
    }
}
