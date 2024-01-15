package com.example.demo.reviews.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Duration For Templates",
        "Diff In Days For Employee According to Template Duration"
})
public interface CheckBeforeReviews {

    @JsonProperty("Duration For Templates")
    Long getTemplate_validity_duration_days();

    @JsonProperty("Diff In Days For Employee According to Template Duration")
    Long getDays_difference();
}
