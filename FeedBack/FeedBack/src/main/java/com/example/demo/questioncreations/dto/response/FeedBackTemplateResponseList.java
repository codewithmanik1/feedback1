package com.example.demo.questioncreations.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Id",
        "Template Name",
        "Questions For",
        "OPD/IPD",
        "Question Type",
        "Department",
        "Question Details",
        "Question Heading",
        "Question Footer",
        "Answer Scale",
        "Duration",
        "Answer Scale Id",
        "Has Conclusion Questions",
        "Conclusion Question Answer"
})
public interface FeedBackTemplateResponseList {

    @JsonProperty("Id")
    Long getId();

    @JsonProperty("Template Name")
    String getTemplateName();

    @JsonProperty("Questions For")
    String getQuestionSetFor();

    @JsonProperty("OPD/IPD")
    String getOpdIpd();

    @JsonProperty("Question Type")
    Boolean getQuestionType();

    @JsonProperty("Department")
    String getDept();

    @JsonProperty("Question Details")
    String getQuestion();

    @JsonProperty("Question Heading")
    String getHeader();

    @JsonProperty("Question Footer")
    String getFooter();

    @JsonProperty("Duration")
    Long getDurationInDays();

    @JsonProperty("Answer Scale")
    String getAnswerScaleName();

    @JsonProperty("Answer Scale Id")
    Long getAnswerScaleId();

    @JsonProperty("Has Conclusion Questions")
    Boolean getHasConclusionQuestions();

    @JsonProperty("Conclusion Question Answer")
    String getConclusionQuestionAnswer();
}
