package com.example.demo.questioncreations.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Template Id",
        "Template Name",
        "Department Id",
        "Department Name",
        "Questions Set For",
        "OPD/IPD",
        "Question Type"
})
public interface TemplateListingForQRCode {

    @JsonProperty("Template Id")
    Long getTemplateId();

    @JsonProperty("Template Name")
    String getTemplateName();

    @JsonProperty("Department Id")
    Long getDepId();

    @JsonProperty("Department Name")
    String getDepName();

    @JsonProperty("Questions Set For")
    String getQuestionSetFor();

    @JsonProperty("OPD/IPD")
    String getOpdIpd();

    @JsonProperty("Question Type")
    String getQuestionType();
}
