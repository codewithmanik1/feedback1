package com.example.demo.feedbacktakenform.service.impl;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.feedbacktakenform.dto.response.TemplateQuestions;
import com.example.demo.feedbacktakenform.service.FeedBackTakenFormService;
import com.example.demo.questioncreations.dto.response.IdLabelValue;
import com.example.demo.questioncreations.repo.DepartmentRepository;
import com.example.demo.questioncreations.repo.QuestionTemplateCreationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackTakenFormServiceImpl implements FeedBackTakenFormService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private QuestionTemplateCreationRepository questionTemplateCreationRepository;

    @Override
    public ResponseEntity<?> departmentDropDownForFeedBackForm(String questionSetFor, String patientType) {
        var response = new ApiResponse<>();

        if(questionSetFor.equals("Patient") && patientType == null) {
            response.responseMethod(HttpStatus.BAD_REQUEST.value(), "Patient type is null", null, null);
            return ResponseEntity.ok(response);
        }

        List<IdLabelValue> getDepartmentDropDownForFeedBackForm = departmentRepository.getDepartmentDropDownForFeedBackForm(questionSetFor,patientType);

        if(!getDepartmentDropDownForFeedBackForm.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Department dropdown fetch successfully", getDepartmentDropDownForFeedBackForm, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), ("Department not link for " + questionSetFor + " template"), null, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getTemplateNameDropDownForFeedBackForm(String questionSetFor, Long depId, String patientType) {
        var response = new ApiResponse<>();

        List<IdLabelValue> getTemplateForFeedBackForm = questionTemplateCreationRepository.getTemplateNameForFeedBackForm(questionSetFor, depId, patientType);

        if(!getTemplateForFeedBackForm.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Template dropdown fetch successfully", getTemplateForFeedBackForm, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), ("Template not link for " + questionSetFor + " template"), null, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getTemplateDropDownAutoComplete(String questionSetFor, Long depId, String patientType, String searchString) {
        var response = new ApiResponse<>();

        List<IdLabelValue> getTemplateDropDownAutoComplete = questionTemplateCreationRepository.getTemplateNameAutoComplete(questionSetFor, depId, patientType, searchString);

        if(!getTemplateDropDownAutoComplete.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Template dropdown fetch successfully", getTemplateDropDownAutoComplete, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), ("Template not link for " + questionSetFor + " template"), null, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getTemplateQuestions(Long tempId) {
        var response = new ApiResponse<>();

        List<TemplateQuestions> getTemplateQuestions = questionTemplateCreationRepository.getTemplateQuestionsById(tempId);

        if(!getTemplateQuestions.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Template questions fetch successfully", getTemplateQuestions, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Template does not present" , null, null);

        return ResponseEntity.ok(response);

    }
}
