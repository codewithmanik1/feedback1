package com.example.demo.questioncreations.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleReqDto {

    private String description;
    private Boolean isActive = true;
    private Long createdBy;
}
