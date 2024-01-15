package com.example.demo.reviews.entity;

import com.example.demo.commonentity.BaseEntity;
import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review_details")
public class ReviewDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private QuestionsCreationsMaster templateId;

    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "question_dep_id")
    private Department questionDeptId;

    @Column(name = "opd_ipd")
    private String opdIpd;

    @Column(name = "answer_rating")
    private Integer answerRating;

}
