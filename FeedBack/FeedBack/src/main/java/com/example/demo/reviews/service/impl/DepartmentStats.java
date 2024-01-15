package com.example.demo.reviews.service.impl;

// Helper class to store total rating and total questions for a department
public class DepartmentStats {
    private int totalRating;
    private int totalQuestions;

    public void addRating(int rating) {
        totalRating += rating;
    }

    public void addQuestion() {
        totalQuestions++;
    }

    public int getQuestionCount() {
        return totalQuestions;
    }

    public double calculateAvgRating() {
        if (totalQuestions == 0) {
            return 0.0; // Handle division by zero
        }
        return (double) totalRating / totalQuestions;
    }
}
