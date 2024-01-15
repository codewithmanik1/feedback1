package com.example.demo.questioncreations.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "Id",
        "Answer Review Type",
        "Answer Type",
        "PreView",
        "Created Date",
        "Last Modified Date"
})
public interface AnswerPatternResponseList {

    @JsonProperty("Id")
    Long getId();

    @JsonProperty("Answer Review Type")
    String getAnsReviewType();

    @JsonProperty("Answer Type")
    String getAnsType();

    @JsonProperty("PreView")
    String getPreView();

    @JsonProperty("Created By")
    Long getCreatedBy();

    @JsonProperty("Created Date")
    String getCreatedDate();

    @JsonProperty("Last Modified By")
    Long getLastModifiedBy();

    @JsonProperty("Last Modified Date")
    String getLastModifiedDate();
}
