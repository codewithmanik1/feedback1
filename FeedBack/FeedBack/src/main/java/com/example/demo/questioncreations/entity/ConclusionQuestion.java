package com.example.demo.questioncreations.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
public class ConclusionQuestion {

    @Length(max = 100)
    private String conclusionQuestion;

    List<saveConclusionAnswers> conclusionAnswers;
}
