package com.example.demo.questioncreations.service;

import com.example.demo.questioncreations.dto.request.AnswerPatternReqDto;
import com.example.demo.questioncreations.dto.request.FeedBackQuestionCreationReqDto;
import com.example.demo.questioncreations.dto.request.QRCodeReqDto;
import com.example.demo.questioncreations.dto.request.RoleReqDto;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;

public interface FeedBackService {

    ResponseEntity<?> saveAndUpdateAnswerPattern(AnswerPatternReqDto answerPatternReqDto);
    ResponseEntity<?> getAnswerPatternList();
    ResponseEntity<?> deleteAnswerPatternScale(Long id);
    ResponseEntity<?> saveTemplateQuestions(FeedBackQuestionCreationReqDto feedBackQuestionCreationReqDto);
    ResponseEntity<?> getQuestionTemplateCreationList();
    ResponseEntity<?> deleteTemplate(Long id);
    ResponseEntity<?> getDepartmentDropDown();

    ResponseEntity<?> getTemplateListingForQRCodeGeneration(Long depId, String searchString);

    ResponseEntity<?> createQRCode(QRCodeReqDto qrCodeReqDto);

    String saveGeneratedQRCode(BufferedImage bufferedImage, QRCodeReqDto qrCodeReqDto);
}
