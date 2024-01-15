package com.example.demo.reviews.repo;

import com.example.demo.reviews.entity.FeedbackUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedBackUserInfoRepository extends JpaRepository<FeedbackUserInfo, Long> {

    @Query(value = "select * from fn_get_patient_employee__data(?1, ?2)", nativeQuery = true)
    List<GetUserInfo> getUserInformation(Integer getDataFrom, String searchString);

}
