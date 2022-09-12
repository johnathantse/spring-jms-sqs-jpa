package com.jms.aws.springbootjmsaws.h2jpa.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "courseName", unique = true)
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
        return "Course: Id=" + id + ", Course Name=" + courseName;
    }
}
