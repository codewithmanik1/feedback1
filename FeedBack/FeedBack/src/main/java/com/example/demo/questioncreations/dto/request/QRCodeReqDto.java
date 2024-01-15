package com.example.demo.questioncreations.dto.request;

import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRCodeReqDto {

    private String description;

    private Department depId;

    private QuestionsCreationsMaster templateId;

    private String redirectLinkForQR;

    private Long createdBy;
}
