package com.example.demo.reviews.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Department Name",
        "Highly Satisfied",
        "Satisfied",
        "Average",
        "Poor",
        "Dissatisfied"
})
public interface DepartmentWiseAvgDataForDashBoard {

    @JsonProperty("Department Name")
    String getDepName();
    @JsonProperty("Highly Satisfied")
    Double getHighly_satisfied_percentage();

    @JsonProperty("Satisfied")
    Double getSatisfied_percentage();

    @JsonProperty("Average")
    Double getAverage_percentage();

    @JsonProperty("Poor")
    Double getPoor_percentage();

    @JsonProperty("Dissatisfied")
    Double getDissatisfied_percentage();
}
