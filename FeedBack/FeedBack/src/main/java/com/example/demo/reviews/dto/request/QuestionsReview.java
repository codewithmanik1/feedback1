package com.example.demo.reviews.dto.request;

import com.example.demo.questioncreations.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsReview {

    private Long questionId;

    private Department questionDeptId;

    private Integer answerRating;
}
