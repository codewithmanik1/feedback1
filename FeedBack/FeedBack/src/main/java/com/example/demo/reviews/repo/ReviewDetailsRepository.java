package com.example.demo.reviews.repo;

import com.example.demo.reviews.entity.ReviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails, Long> {
}
