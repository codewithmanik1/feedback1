package com.example.demo.reviews.service.impl;

import com.example.demo.reviews.service.ReviewService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewServiceImpl reviewServiceimpl;

    @After("execution(* com.example.demo.reviews.service.ReviewService.saveReview(..))")
    public void afterSaveReview() {
        System.out.println("in aspect");
        reviewService.saveDepartmentReviews(reviewServiceimpl.getReviewRequestDto());
    }

}
