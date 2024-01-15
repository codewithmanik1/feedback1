package com.example.demo.reviews.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Id",
        "Employee Name"
})
public interface EmployeeList {

    @JsonProperty("Id")
    Long getId();

    @JsonProperty("Employee Name")
    String getEmployeeName();
}
