package com.example.demo.reviews.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
        @Table(name="feedback_user_info")
public class FeedbackUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_Name")
    private String patientName;

    @Column(name="patient_phone_number")
    private String patientPhoneNumber;
}
