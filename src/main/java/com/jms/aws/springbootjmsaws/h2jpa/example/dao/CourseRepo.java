package com.jms.aws.springbootjmsaws.h2jpa.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.jms.aws.springbootjmsaws.h2jpa.example.models.Course;

public interface CourseRepo extends CrudRepository<Course, Integer> {

}
