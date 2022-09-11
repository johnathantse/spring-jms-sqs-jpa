package com.jms.aws.springbootjmsaws.h2jpa.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseName(String courseName);

}
