package com.example.demo.feedbacktakenform.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Id",
        "Headers",
        "Footer",
        "Questions",
        "HasConclusionQuestions",
        "ConclusionQuestionAns",
        "AnswerType",
        "AnsPatternScale",
        "templateName"
})
public interface TemplateQuestions {

    @JsonProperty("Id")
    Long getTemplate_id();

    @JsonProperty("Headers")
    String getHeaders();

    @JsonProperty("Footer")
    String getFooter();

    @JsonProperty("Questions")
    String getQuestions();

    @JsonProperty("HasConclusionQuestions")
    Boolean getHasConclusionQuestions();

    @JsonProperty("conclusionQuestionAns")
    String getConclusionQuestionAns();

    @JsonProperty("AnswerType")
    String getAnswerType();

    @JsonProperty("AnsPatternScale")
    String getAnsPatternScale();

    @JsonProperty("templateName")
    String getTemplateName();

}
