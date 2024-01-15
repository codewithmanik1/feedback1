package com.example.demo.questioncreations.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Id",
        "Label",
        "Value"
})
public interface RoleForSelectResponseDto {

    @JsonProperty("Id")
    Long getId();

    @JsonProperty("Label")
    String getLabel();

    @JsonProperty("Value")
    String getValue();
}
