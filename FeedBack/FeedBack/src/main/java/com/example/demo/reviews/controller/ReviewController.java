package com.example.demo.reviews.controller;

import com.example.demo.commonentity.Employee;
import com.example.demo.reviews.dto.request.ReviewRequestDto;
import com.example.demo.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/saveReviews")
    public ResponseEntity<?> saveReviews(@RequestBody ReviewRequestDto reviewRequestDto){
        System.out.println(reviewRequestDto.toString());
        return reviewService.checkEligibilityBeforeReviews(reviewRequestDto);
    }

    @PostMapping("getPatientUserInformationFrom")
    public ResponseEntity<?> getPatientOrEmployeeInformationAutocomplete(@RequestParam (required = false) Integer dataFrom, @RequestParam (required = false)  String searchString){
        return reviewService.getPatientOrEmployeeInformationAutoComplete(dataFrom, searchString);
    }

    @GetMapping("/getDepartmentWiseAvgRating/{dataFor}")
    public ResponseEntity<?> getDepartmentWiseAvgRating(@PathVariable String dataFor){
        return reviewService.getDepartmentReviewsAvgRatingForDashBoard(dataFor);
    }

    @GetMapping("/getPatientWiseAvgRating")
    public ResponseEntity<?> getPatientWiseAvgRating(){
        return reviewService.getPatientReviewsAvgRatingForDashBoard();
    }

    @GetMapping("/getStaffWiseAvgRating")
    public ResponseEntity<?> getStaffWiseAvgRating(){
        return reviewService.getStaffReviewsAvgRatingForDashBoard();
    }

    @GetMapping("/getOpdPatientDateWiseAvgRating")
    public ResponseEntity<?> getOpdPatientAvgDateWiseRating(){
        return reviewService.getOpdPatientAvgDateWiseRatingForDashBoard();
    }

    @GetMapping("/getIpdPatientDateWiseRating")
    public ResponseEntity<?> getIpdPatientDateWiseRating(){
        return reviewService.getIpdPatientAvgDateWiseRatingForDashBoard();
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        return reviewService.saveEmployee(employee);
    }
}
