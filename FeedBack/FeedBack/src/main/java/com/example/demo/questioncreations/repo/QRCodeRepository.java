package com.example.demo.questioncreations.repo;

import com.example.demo.questioncreations.entity.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
