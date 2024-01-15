package com.example.demo.reviews.service;


import com.example.demo.commonentity.Employee;
import com.example.demo.reviews.dto.request.ReviewRequestDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {

    ResponseEntity<?> checkEligibilityBeforeReviews(ReviewRequestDto reviewRequestDto);
    ResponseEntity<?> saveReview(ReviewRequestDto reviewRequestDto);
    void saveDepartmentReviews(ReviewRequestDto reviewRequestDto);
    ResponseEntity<?> getDepartmentReviewsAvgRatingForDashBoard(String dataFor);

    ResponseEntity<?> getPatientReviewsAvgRatingForDashBoard();

    ResponseEntity<?> getStaffReviewsAvgRatingForDashBoard();

    ResponseEntity<?> getOpdPatientAvgDateWiseRatingForDashBoard();
    ResponseEntity<?> getIpdPatientAvgDateWiseRatingForDashBoard();

    ResponseEntity<?> getPatientOrEmployeeInformationAutoComplete(Integer getDataFrom, String searchString);

    ResponseEntity<?> saveEmployee(Employee employee);

    ResponseEntity<?> getAllEmployess();

}
