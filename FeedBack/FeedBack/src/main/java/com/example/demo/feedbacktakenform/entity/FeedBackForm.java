package com.example.demo.feedbacktakenform.entity;

import com.example.demo.commonentity.BaseEntity;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@Entity
//@Table(name = "feedback_form")
public class FeedBackForm extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //employee , staff , patient id

    @ManyToOne
    @JoinColumn(name = "question_creation_template_id")
    private QuestionsCreationsMaster questionsCreationsMaster;

    @Column(name = "ratings", columnDefinition = "json")
    private List<SaveQuestionsRatings> questionsRatings;

}
