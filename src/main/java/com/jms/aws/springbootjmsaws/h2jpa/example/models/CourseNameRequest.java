package com.jms.aws.springbootjmsaws.h2jpa.example.models;

public class CourseNameRequest {
    String originalName;
    String newName;

    public CourseNameRequest(String originalName, String newName) {
        this.originalName = originalName;
        this.newName = newName;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getNewName() {
        return this.newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
