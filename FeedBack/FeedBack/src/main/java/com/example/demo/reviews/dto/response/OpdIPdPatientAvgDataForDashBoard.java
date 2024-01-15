package com.example.demo.reviews.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({
        "Date",
        "Total FeedBack",
        "Highly Satisfied",
        "Satisfied",
        "Average",
        "Poor",
        "Dissatisfied"
})
public interface OpdIPdPatientAvgDataForDashBoard {

    @JsonProperty("Date")
    String getDatee();

    @JsonProperty("Total Feedback")
    Long getTotal_count();

    @JsonProperty("Highly Satisfied")
    Integer getHighly_satisfied_count();

    @JsonProperty("Satisfied")
    Integer getSatisfied_count();

    @JsonProperty("Average")
    Integer getAverage_count();

    @JsonProperty("Poor")
    Integer getPoor_count();

    @JsonProperty("Dissatisfied")
    Integer getDissatisfied_count();
}
