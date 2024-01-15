package com.example.demo.questioncreations.dto.request;

import com.example.demo.questioncreations.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class FeedBackQuestionCreationReqDto {

    private Long id;

    private String questionSetFor;

    @Length(max = 100)
    private String templateName;

    @Length(max = 250)
    private String questionSetHeading;

    @Length(max = 250)
    private String questionSetFootNote;

    private Long templateDurationInDays;

    private Department departmentId;

    @NotNull(message = "Answer pattern cannot be null")
    private AnswerPattern answerPatternScaleId;   // 1 : button , 2 :scale


    private List<SaveQuestions> questions;

    private Boolean hasConclusionQues;

    private ConclusionQuestion conclusionQueAnswers;

    private Character patientType;        // 1: OPD , 2: IPD

    private Boolean isGeneral;

    private Long createdBy;

    private Long lastModifiedBy;

}
