package com.example.demo.feedbacktakenform.controller;

import com.example.demo.feedbacktakenform.service.FeedBackTakenFormService;
import org.apache.tomcat.util.buf.CharChunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/feedBackForm")
public class FeedBackTakenFormController {

    @Autowired
    private FeedBackTakenFormService feedBackTakenFormService;


    @GetMapping("/getDepartmentDropDownForFeedBackForm")
    public ResponseEntity<?> getDepartmentDropDownForFeedBackForm(@RequestParam String templateFor,@RequestParam(required = false) String patientType) {
        return feedBackTakenFormService.departmentDropDownForFeedBackForm(templateFor, patientType);
    }

    @GetMapping("/getTemplateForFeedBackFormDropDown")
    public ResponseEntity<?> getTemplateForFeedBackFormDropDown(@RequestParam String templateFor, @RequestParam(required = false) Long depId, @RequestParam(required = false) String patientType){
        return feedBackTakenFormService.getTemplateNameDropDownForFeedBackForm(templateFor, depId, patientType);
    }

    @GetMapping("getTemplateDropDownAutoComplete")
    public ResponseEntity<?> getTemplateAutoComplete(@RequestParam String templateFor, @RequestParam(required = false) Long depId, @RequestParam(required = false) String patientType, @RequestParam(required = false) String searchString){
        System.out.println("dep Id " +depId);
        return feedBackTakenFormService.getTemplateDropDownAutoComplete(templateFor, depId, patientType, searchString);
    }

    @GetMapping("/getTemplateQuestions")
    public ResponseEntity<?> getTemplateQuestions(@RequestParam Long templateId){
        return feedBackTakenFormService.getTemplateQuestions(templateId);
    }
}
