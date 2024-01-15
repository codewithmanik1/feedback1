package com.example.demo.questioncreations.dto.request;

import com.example.demo.questioncreations.entity.AnswerPatternReq;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerPatternReqDto {

    private Long id;

    List<AnswerPatternListReqDto> answerPatternListReqDtoList;

    private String answerReviewType;

    private String answerType;

    private Long createdBy;

    private Long lastModifiedBy;
}
