package com.example.demo.questioncreations.controller;

import com.example.demo.questioncreations.dto.request.AnswerPatternReqDto;
import com.example.demo.questioncreations.dto.request.FeedBackQuestionCreationReqDto;
import com.example.demo.questioncreations.dto.request.QRCodeReqDto;
import com.example.demo.questioncreations.dto.request.RoleReqDto;
import com.example.demo.questioncreations.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionsCreations")
public class FeedBackFormMastersController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/saveAndUpdateAnswerPattern")
    public ResponseEntity<?> saveAnswerPattern(@RequestBody AnswerPatternReqDto answerPatternReqDto){
        return feedBackService.saveAndUpdateAnswerPattern(answerPatternReqDto);
    }

    @GetMapping("/getAnswerPatternList")
    public ResponseEntity<?> getAnswerPatternList(){
        return feedBackService.getAnswerPatternList();
    }

    @DeleteMapping("/deleteAnswerPatternScale/{id}")
    public ResponseEntity<?> deleteAnswerPatternScale(@PathVariable Long id){
        return feedBackService.deleteAnswerPatternScale(id);
    }

    @PostMapping("/createAndSaveFeedBackTemplate")
    public ResponseEntity<?> saveFeedBackQuestionsTemplate(@RequestBody FeedBackQuestionCreationReqDto feedBackQuestionCreationReqDto){
        return feedBackService.saveTemplateQuestions(feedBackQuestionCreationReqDto);
    }

    @PostMapping("/getQuestionTemplateCreationList")
    public ResponseEntity<?> getQuestionTemplateCreationList(){
        return feedBackService.getQuestionTemplateCreationList();
    }

    @DeleteMapping("/deleteFeedBackQuestionTemplate/{id}")
    public ResponseEntity<?> deleteQuestionTemplate(@PathVariable Long id){
        return feedBackService.deleteTemplate(id);
    }

    @GetMapping("getDepartmentDropDown")
    public ResponseEntity<?> getDepartmentDropDown(){
        return feedBackService.getDepartmentDropDown();
    }

    @PostMapping("/getTemplateListingForQRCodeGeneration")
    public ResponseEntity<?> getTemplateForQRCode(@RequestParam (required = false) Long depId, @RequestParam (required = false) String searchString){
        return feedBackService.getTemplateListingForQRCodeGeneration(depId, searchString);
    }

    @PostMapping("/generateQRCode")
    public ResponseEntity<?> generateQRCode(@RequestBody QRCodeReqDto qrCodeReqDto){
        return feedBackService.createQRCode(qrCodeReqDto);
    }
}
