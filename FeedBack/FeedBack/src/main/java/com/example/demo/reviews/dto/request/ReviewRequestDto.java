package com.example.demo.reviews.dto.request;

import com.example.demo.commonentity.Employee;
import com.example.demo.commonentity.PatientInfo;
import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import com.example.demo.reviews.entity.FeedbackUserInfo;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewRequestDto {

    private Long id;

    private PatientInfo patientId;

    private Employee employeeId;

    private String opdIpd;

    private Department templateDepartmentId;

    private QuestionsCreationsMaster templateId;

    private List<QuestionsReview> questionsReviews;

    private Long createdBy;

    private Long lastModifiedBy;

    private String conclusionAns;

    private Integer reviewByInfoFrom ;

    private FeedbackUserInfo localUserId;

    private String patientName;

    private String phoneNumber;
}
