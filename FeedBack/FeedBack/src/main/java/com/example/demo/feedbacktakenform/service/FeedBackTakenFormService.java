package com.example.demo.feedbacktakenform.service;


import org.apache.tomcat.util.buf.CharChunk;
import org.springframework.http.ResponseEntity;

public interface FeedBackTakenFormService {

    ResponseEntity<?> departmentDropDownForFeedBackForm(String questionSetFor, String patientType);

    ResponseEntity<?> getTemplateNameDropDownForFeedBackForm(String questionSetFor, Long depId, String patientType);

    ResponseEntity<?> getTemplateDropDownAutoComplete(String questionSetFor, Long depId, String patientType, String searchString);

    ResponseEntity<?> getTemplateQuestions(Long tempId);
}
