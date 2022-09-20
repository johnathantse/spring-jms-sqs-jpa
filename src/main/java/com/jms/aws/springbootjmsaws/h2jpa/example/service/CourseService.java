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

    public Course updateCourse(Course course) {
        Course originalCourse = getExistingCourseId(course.getId());
        if (originalCourse != null) {
            courseRepo.save(course);
            sqsProducer.sendMessage("Successfully updated course from: " + originalCourse + " to: " + course);
        } else {
            sqsProducer.sendMessage("Unable to find and update course " + originalCourse);
        }
        return course;
    }

    public Course deleteCourse(Course course) {
        Course courseToDelete = getExistingCourseId(course.getId());
        if (courseToDelete != null) {
            courseRepo.delete(courseToDelete);
            sqsProducer.sendMessage("Successfully deleted course: " + course);
        }
        return courseToDelete;
    }

    public Course getCourseByName(String courseName) {
        Course course = getExistingCourseByName(courseName);
        return course;
    }

    public Course deleteCourseByName(String courseName) {
        Course course = getExistingCourseByName(courseName);
        if (course != null) {
            sqsProducer.sendMessage("Successfully deleted course: " + course);
            courseRepo.delete(course);
        } else {
            sqsProducer.sendMessage("Unable to find and delete course named " + courseName);
        }
        return course;
    }

    public Course updateCourseByName(String originalCourseName, String newCourseName) {
        Course course = getExistingCourseByName(originalCourseName);
        if (course != null) {
            course.setCourseName(newCourseName);
            courseRepo.save(course);
            sqsProducer.sendMessage("Successfully set course name from " + originalCourseName + " to " + newCourseName);
        } else {
            sqsProducer.sendMessage("Unable to find and update course named " + originalCourseName);
        }
        return course;
    }

    /**
     * Check of course id exists. If it does, return course, else null.
     * 
     * @param courseId
     * @return the course object or null if not found
     */
    public Course getExistingCourseId(int courseId) {
        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        Course course = optionalCourse.orElse(null);
        if (course == null) {
            return null;
        }
        return course;
    }

    /**
     * Check if course name exists. If it does, return course, else null.
     * 
     * @param courseName
     * @return the course object or null if not found
     */
    public Course getExistingCourseByName(String courseName) {
        Optional<Course> optionalCourse = courseRepo.findByCourseName(courseName);
        Course course = optionalCourse.orElse(null);
        if (course == null) {
            return null;
        }
        return course;
    }
}
