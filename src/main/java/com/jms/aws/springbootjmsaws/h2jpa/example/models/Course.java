package com.jms.aws.springbootjmsaws.h2jpa.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Course")
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String courseName;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course: Id=" + id + " , Course Name=" + courseName;
    }
}
