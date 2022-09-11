package com.jms.aws.springbootjmsaws.h2jpa.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jms.aws.springbootjmsaws.h2jpa.example.dao.CourseRepo;
import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;
import com.jms.aws.springbootjmsaws.sqs.SqsProducer;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    SqsProducer sqsProducer;

    public Course addCourse(Course course) {
        Course newCourse = courseRepo.save(course);
        sqsProducer.sendMessage("New course added: " + course);
        return newCourse;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepo.findAll().forEach(courses::add);
        return courses;
    }

    public Course getCourseById(Integer id) {
        return courseRepo.findById(id).orElse(null);
    }

    public Optional<Course> updateCourse(Course course) {
        Optional<Course> courseToUpdate = courseRepo.findById(course.getId());
        if (!courseToUpdate.isPresent()) {
            return null;
        }
        courseRepo.save(course);
        sqsProducer.sendMessage("Successfully updated course to: " + course);
        return courseToUpdate;
    }

    public Optional<Course> deleteCourse(Course course) {
        Optional<Course> courseToDelete = courseRepo.findById(course.getId());
        if (!courseToDelete.isPresent()) {
            return null;
        }
        courseRepo.delete(course);
        sqsProducer.sendMessage("Successfully deleted course: " + course);
        return courseToDelete;
    }

    public Course updateCourseByName(String courseName) {
        Optional<Course> courseToUpdate = courseRepo.findByCourseName(courseName);
        Course course = courseToUpdate.orElse(null);
        if (course == null) {
            return null;
        }
        courseRepo.save(course);
        return course;

    }
}
