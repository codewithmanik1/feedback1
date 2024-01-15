package com.example.demo.questioncreations.service.impl;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.questioncreations.dto.request.AnswerPatternListReqDto;
import com.example.demo.questioncreations.dto.request.AnswerPatternReqDto;
import com.example.demo.questioncreations.dto.request.FeedBackQuestionCreationReqDto;
import com.example.demo.questioncreations.dto.request.QRCodeReqDto;
import com.example.demo.questioncreations.dto.response.AnswerPatternResponseList;
import com.example.demo.questioncreations.dto.response.FeedBackTemplateResponseList;
import com.example.demo.questioncreations.dto.response.IdLabelValue;
import com.example.demo.questioncreations.dto.response.TemplateListingForQRCode;
import com.example.demo.questioncreations.entity.AnswerPattern;
import com.example.demo.questioncreations.entity.AnswerPatternReq;
import com.example.demo.questioncreations.entity.QRCode;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;

import com.example.demo.questioncreations.repo.AnswerPatternRepository;
import com.example.demo.questioncreations.repo.DepartmentRepository;
import com.example.demo.questioncreations.repo.QRCodeRepository;
import com.example.demo.questioncreations.repo.QuestionTemplateCreationRepository;
import com.example.demo.questioncreations.service.FeedBackService;
import com.spire.barcode.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class FeedBackServiceImpl implements FeedBackService {


    @Autowired
    private AnswerPatternRepository answerPatternRepository;

    @Autowired
    private QuestionTemplateCreationRepository questionTemplateCreationRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    private String STORAGE_PATH = "C:\\Manik_Materials\\";

    @Override
    public ResponseEntity<?> saveAndUpdateAnswerPattern(AnswerPatternReqDto answerPatternReqDto) {
        var response = new ApiResponse<>();

        List<AnswerPatternListReqDto> answerPatternListReqDtoList = answerPatternReqDto.getAnswerPatternListReqDtoList();

        AnswerPattern answerPattern = new AnswerPattern();
        if (answerPatternListReqDtoList != null && !answerPatternListReqDtoList.isEmpty()) {
            if (answerPatternReqDto.getId() != null) {
                Optional<AnswerPattern> existingAnswerPatternScale = answerPatternRepository.findById(answerPatternReqDto.getId());
                if (existingAnswerPatternScale.isPresent()) {
                    answerPattern = existingAnswerPatternScale.get();
                    answerPattern.setLastModifiedBy(answerPatternReqDto.getLastModifiedBy());
                    answerPattern.setLastModifiedDate(LocalDateTime.now());
                    response.responseMethod(HttpStatus.OK.value(), "Answer Pattern updated successfully", null, null);
                }
            } else {
                answerPattern.setCreatedBy(answerPatternReqDto.getCreatedBy());
                answerPattern.setCreatedDate(LocalDateTime.now());
                answerPattern.setLastModifiedBy(answerPatternReqDto.getCreatedBy());
                answerPattern.setLastModifiedDate(LocalDateTime.now());
                response.responseMethod(HttpStatus.OK.value(), "Answer Pattern saved successfully", null, null);
            }
            List<AnswerPatternReq> answerPatternReqList = new ArrayList<>();
            for (AnswerPatternListReqDto data : answerPatternListReqDtoList) {
                AnswerPatternReq answerPatternReq = new AnswerPatternReq();
                answerPatternReq.setReviewLabel(data.getReviewLabel());
                answerPatternReq.setValue(data.getValue());
                answerPatternReqList.add(answerPatternReq);
            }

            //set List for jsonb
            answerPattern.setAnswerPatternReq(answerPatternReqList);
            answerPattern.setAnswerReviewType(answerPatternReqDto.getAnswerReviewType());
            answerPattern.setAnswerType(answerPatternReqDto.getAnswerType());
            answerPatternRepository.save(answerPattern);
        } else {
            response.responseMethod(HttpStatus.BAD_REQUEST.value(), "Answer Scale cannot be null", null, null);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> getAnswerPatternList() {
        var response = new ApiResponse<>();

        List<AnswerPatternResponseList> answerPatternResponseList = answerPatternRepository.answerPatternList();
        if (!answerPatternResponseList.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Answer Pattern Lists fetch successfully", answerPatternResponseList, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Answer Pattern list does not found", null, null);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> saveTemplateQuestions(FeedBackQuestionCreationReqDto feedBackQuestionCreationReqDto) {
        var response = new ApiResponse<>();

        QuestionsCreationsMaster questionsCreationsMaster = new QuestionsCreationsMaster();

        if (feedBackQuestionCreationReqDto.getQuestions() != null && !feedBackQuestionCreationReqDto.getQuestions().isEmpty()) {
        if(feedBackQuestionCreationReqDto.getId() != null){
            Optional<QuestionsCreationsMaster> existingQuestionsCreationsMaster = questionTemplateCreationRepository.findById(feedBackQuestionCreationReqDto.getId());
            questionsCreationsMaster = existingQuestionsCreationsMaster.get();
            questionsCreationsMaster.setLastModifiedBy(feedBackQuestionCreationReqDto.getLastModifiedBy());
            questionsCreationsMaster.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.OK.value(), "FeeBack Questions / Template creation updated successfully", null, null);
        }else{
            questionsCreationsMaster.setCreatedBy(feedBackQuestionCreationReqDto.getCreatedBy());
            questionsCreationsMaster.setCreatedDate(LocalDateTime.now());
            questionsCreationsMaster.setLastModifiedBy(feedBackQuestionCreationReqDto.getCreatedBy());
            questionsCreationsMaster.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.OK.value(), "FeeBack Questions / Template creation saved successfully", null, null);
        }

            //QuestionsCreationsMaster questionsCreationsMaster = new QuestionsCreationsMaster();
            questionsCreationsMaster.setQuestionSetFor(feedBackQuestionCreationReqDto.getQuestionSetFor());
            questionsCreationsMaster.setPatientType(feedBackQuestionCreationReqDto.getPatientType());
            questionsCreationsMaster.setTemplateName(feedBackQuestionCreationReqDto.getTemplateName());
            questionsCreationsMaster.setQuestionSetHeading(feedBackQuestionCreationReqDto.getQuestionSetHeading());
            questionsCreationsMaster.setQuestionSetFootNote(feedBackQuestionCreationReqDto.getQuestionSetFootNote());
            if (feedBackQuestionCreationReqDto.getIsGeneral()) {
                questionsCreationsMaster.setDepartmentId(null);
            } else {
                questionsCreationsMaster.setDepartmentId(feedBackQuestionCreationReqDto.getDepartmentId());
            }
            questionsCreationsMaster.setIsGeneral(feedBackQuestionCreationReqDto.getIsGeneral());
            questionsCreationsMaster.setTemplateDurationInDays(feedBackQuestionCreationReqDto.getTemplateDurationInDays());
            questionsCreationsMaster.setAnswerPatternScaleId(feedBackQuestionCreationReqDto.getAnswerPatternScaleId());
            questionsCreationsMaster.setQuestions(feedBackQuestionCreationReqDto.getQuestions());

            if (feedBackQuestionCreationReqDto.getHasConclusionQues()) {
                questionsCreationsMaster.setHasConclusionQues(true);
                questionsCreationsMaster.setConclusionQueAnswers(feedBackQuestionCreationReqDto.getConclusionQueAnswers());
            } else {
                questionsCreationsMaster.setHasConclusionQues(false);
            }
            questionTemplateCreationRepository.save(questionsCreationsMaster);
        } else {
            response.responseMethod(HttpStatus.BAD_REQUEST.value(), "Questions are not present", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getQuestionTemplateCreationList() {
        var response = new ApiResponse<>();

        List<FeedBackTemplateResponseList> feedbackQuestionTemplate = questionTemplateCreationRepository.getQuestionTemplateCreationList();
        if (!feedbackQuestionTemplate.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Feedback question template fetch successfully", feedbackQuestionTemplate, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Feedback question template list does not found", null, null);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> getDepartmentDropDown() {
        var response = new ApiResponse<>();

        List<IdLabelValue> departmentDropDownLists = departmentRepository.getDepartmentDropDownService();
        if (!departmentDropDownLists.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Department dropdown fetch successfully", departmentDropDownLists, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Department are not available", null, null);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> deleteAnswerPatternScale(Long id) {
        var response = new ApiResponse<>();

        Optional<AnswerPattern> existingAnswerPatternScale = answerPatternRepository.findById(id);

        if (existingAnswerPatternScale.isPresent()) {
            existingAnswerPatternScale.get().setIsDelete(true);
            answerPatternRepository.save(existingAnswerPatternScale.get());
            response.responseMethod(HttpStatus.OK.value(), "Scale deleted successfully", null, null);
        } else {
            response.responseMethod(HttpStatus.OK.value(), "Scale not found", null, null);
        }
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> deleteTemplate(Long id) {
        var response = new ApiResponse<>();

        Optional<QuestionsCreationsMaster> existingQuestionCreationTemplate = questionTemplateCreationRepository.findById(id);

        if (existingQuestionCreationTemplate.isPresent()) {
            existingQuestionCreationTemplate.get().setIsDelete(true);
            questionTemplateCreationRepository.save(existingQuestionCreationTemplate.get());
            response.responseMethod(HttpStatus.OK.value(), "Template Question deleted successfully", null, null);
        } else {
            response.responseMethod(HttpStatus.OK.value(), "Template not found", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getTemplateListingForQRCodeGeneration(Long depId, String searchString) {
        var response = new ApiResponse<>();

        List<TemplateListingForQRCode> templateListingForQRCodeGeneration = questionTemplateCreationRepository.getTemplateListForQRCodeGeneration(depId, searchString);
        if(templateListingForQRCodeGeneration != null)
            response.responseMethod(HttpStatus.OK.value(), "Template Listing Fetch Successfully", templateListingForQRCodeGeneration, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Template Does Not Found", null, null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> createQRCode(QRCodeReqDto qrCodeReqDto) {
        var response = new ApiResponse<>();

        BarcodeSettings settings = new BarcodeSettings();
        settings.setType(BarCodeType.QR_Code);
        settings.setQRCodeDataMode(QRCodeDataMode.Alpha_Number);
        settings.setQRCodeECL(QRCodeECL.Q);
        settings.setData(qrCodeReqDto.getRedirectLinkForQR());
        settings.setShowText(false);
        settings.setX(2);
        settings.hasBorder(false);

        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
        BufferedImage bufferedImage = barCodeGenerator.generateImage();

        // Save generated QR code
        String imageInBase64 = saveGeneratedQRCode(bufferedImage, qrCodeReqDto);

        response.responseMethod(HttpStatus.OK.value(), "QR Code generated Successfully for template", imageInBase64, null);
        return ResponseEntity.ok(response);
    }

    @Override
    public String saveGeneratedQRCode(BufferedImage bufferedImage, QRCodeReqDto qrCodeReqDto) {
        String fileName = null;
        String imageInBase64 = null;
        QRCode qrCode = new QRCode();
        qrCode.setDescription(qrCodeReqDto.getDescription());
        qrCode.setDepartmentId(qrCodeReqDto.getDepId());
        qrCode.setTemplateId(qrCodeReqDto.getTemplateId());
        qrCode.setRedirectLink(qrCodeReqDto.getRedirectLinkForQR());
        qrCode.setCreatedBy(qrCodeReqDto.getCreatedBy());
        qrCode.setCreatedDate(LocalDateTime.now());
        qrCode.setLastModifiedBy(qrCodeReqDto.getCreatedBy());
        qrCode.setLastModifiedDate(LocalDateTime.now());
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", bos);
            byte[] imageBytes = bos.toByteArray();

            //convert image into base64
            imageInBase64 = Base64.getEncoder().encodeToString(imageBytes);

            // Generate unique file name
            fileName = "image_" + System.currentTimeMillis() + ".png";
            System.out.println("fileName QR "+ fileName);

            //data saved to the db;
            qrCode.setQrCodePath(fileName);
            qrCodeRepository.save(qrCode);

            // Create directory if it doesn't exist
            Path directoryPath = Paths.get(STORAGE_PATH);
            Files.createDirectories(directoryPath);

            // Save Image to local storage
            Path filePath = directoryPath.resolve(fileName);
            Files.write(filePath, imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageInBase64;
    }
}
