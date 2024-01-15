package com.example.demo.questioncreations.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "label",
        "value"
})
public interface IdLabelValue {

    @JsonProperty("id")
    Long getId();

    @JsonProperty("label")
    String getLabel();

    @JsonProperty("value")
    String getValue();
}
