package com.example.demo.reviews.repo;

import com.example.demo.reviews.dto.response.CheckBeforeReviews;
import com.example.demo.reviews.dto.response.OpdIPdPatientAvgDataForDashBoard;
import com.example.demo.reviews.dto.response.PatientWiseTotalAvgDataForDashBoard;
import com.example.demo.reviews.dto.response.StaffWiseTotalAvgDataForDashBoard;
import com.example.demo.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query(value = "select * from fn_check_before_review(?1, ?2)", nativeQuery = true)
    List<CheckBeforeReviews> checkBeforeReview(Long staffId, Long templateId);


    @Query(value = "select * from calculate_patient_statistics()", nativeQuery = true)
    List<PatientWiseTotalAvgDataForDashBoard> getPatientReviewsAvgRating();

    @Query(value = "select * from calculate_staff_statistics()", nativeQuery = true)
    List<StaffWiseTotalAvgDataForDashBoard> getStaffReviewsAvgRating();

    @Query(value = "select * from calculate_opd_patient_statistics()", nativeQuery = true)
    List<OpdIPdPatientAvgDataForDashBoard> getOpdPatientAvgDateWiseRating();

    @Query(value = "select * from calculate_ipd_patient_statistics()", nativeQuery = true)
    List<OpdIPdPatientAvgDataForDashBoard> getIpdPatientAvgDateWiseRating();
}
