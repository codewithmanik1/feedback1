package com.example.demo.reviews.repo;

import com.example.demo.reviews.dto.response.DepartmentWiseAvgDataForDashBoard;
import com.example.demo.reviews.entity.DepartmentWiseAvgRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentWiseAvgRatingRepository extends JpaRepository<DepartmentWiseAvgRating, Long> {

    @Query(value = "select * from calculate_department_statistics(?1)", nativeQuery = true)
    List<DepartmentWiseAvgDataForDashBoard> getDepartmentWiseAvgRating(String dataFor);
}
