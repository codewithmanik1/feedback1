package com.example.demo.reviews.repo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Id",
        "Label",
        "Value"
})
public interface GetUserInfo {

    @JsonProperty("Id")
    Long getId();

    @JsonProperty("Label")
    String getLabel();

    @JsonProperty("Value")
    String getValue();

}
