package com.example.demo.reviews.service.impl;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.commonentity.Employee;
import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.repo.DepartmentRepository;
import com.example.demo.reviews.dto.request.QuestionsReview;
import com.example.demo.reviews.dto.request.ReviewRequestDto;
import com.example.demo.reviews.dto.response.*;
import com.example.demo.reviews.entity.*;
import com.example.demo.reviews.repo.*;
import com.example.demo.reviews.service.ReviewService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Getter
@Setter
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewDetailsRepository reviewDetailsRepository;

    @Autowired
    private DepartmentWiseAvgRatingRepository departmentWiseAvgRatingRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private FeedBackUserInfoRepository feedBackUserInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    ReviewRequestDto reviewRequestDto = null;

    @Override
    public ResponseEntity<?> checkEligibilityBeforeReviews(ReviewRequestDto reviewRequestDto) {
        var response = new ApiResponse<>();

        List<CheckBeforeReviews> getEligibilityData = reviewRepository.checkBeforeReview(
                reviewRequestDto.getEmployeeId().getId(),
                reviewRequestDto.getTemplateId().getId()
        );

        if(reviewRequestDto.getReviewByInfoFrom() == null){
            if (getEligibilityData == null || getEligibilityData.isEmpty()) {
                // No previous entries, save the review
                ResponseEntity<?> getResult = saveReview(reviewRequestDto);
                if (getResult.getStatusCode().is2xxSuccessful()) {
                    response.responseMethod(HttpStatus.OK.value(), "Review Saved Successfully", null, null);
                }
            } else {
                CheckBeforeReviews eligibility = getEligibilityData.get(0);

                if (eligibility.getDays_difference() >= eligibility.getTemplate_validity_duration_days()) {
                    // Eligible to submit a new review
                    ResponseEntity<?> getResult = saveReview(reviewRequestDto);
                    if (getResult.getStatusCode().is2xxSuccessful()) {
                        response.responseMethod(HttpStatus.OK.value(), "Review Saved Successfully", null, null);
                    }
                } else {
                    // Not eligible to submit a new review
                    long daysRemaining = eligibility.getTemplate_validity_duration_days() - eligibility.getDays_difference();
                    response.responseMethod(
                            HttpStatus.BAD_REQUEST.value(),
                            "You Are Already Filled This Review, You Are Eligible After " + daysRemaining + " days",
                            null,
                            null
                    );
                }
            }
        }else{
            if(reviewRequestDto.getReviewByInfoFrom() <= 2){
                ResponseEntity<?> getResult = saveReview(reviewRequestDto);
                if (getResult.getStatusCode().is2xxSuccessful()) {
                    response.responseMethod(HttpStatus.OK.value(), "Review Saved Successfully", null, null);
                    return ResponseEntity.ok(response);
                }
            }
        }
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> saveReview(ReviewRequestDto reviewRequestDto) {
        System.out.println(reviewRequestDto.toString());
        System.out.println("In save review");
        this.reviewRequestDto = reviewRequestDto;
        var response = new ApiResponse<>();
        Review review = new Review();

        int totalAnswerRating = 0;
        int totalQuestions = 0;

        for (QuestionsReview ratings : reviewRequestDto.getQuestionsReviews()) {
            if (ratings.getAnswerRating() != null) {
                totalAnswerRating += ratings.getAnswerRating();
                totalQuestions++;
            }
        }
        review.setReviewByInfoFrom(reviewRequestDto.getReviewByInfoFrom());
        System.out.println("Review By Info "+ reviewRequestDto.getReviewByInfoFrom());

        if(reviewRequestDto.getReviewByInfoFrom() != null){
            if(reviewRequestDto.getReviewByInfoFrom() == 2){
                FeedbackUserInfo feedbackUserInfo = new FeedbackUserInfo();
                feedbackUserInfo.setPatientName(reviewRequestDto.getPatientName());
                feedbackUserInfo.setPatientPhoneNumber(reviewRequestDto.getPhoneNumber());
                FeedbackUserInfo saveUserInfo = feedBackUserInfoRepository.save(feedbackUserInfo);
                review.setPatientIdLocal(saveUserInfo);
                review.setPatientId(null);
            }else {
                if (reviewRequestDto.getReviewByInfoFrom() == 1) {
                    review.setPatientIdLocal(reviewRequestDto.getLocalUserId());
                    review.setPatientId(null);
                } else if (reviewRequestDto.getReviewByInfoFrom() == 0) {
                    review.setPatientId(reviewRequestDto.getPatientId());
                    review.setPatientIdLocal(null);
                }
            }
        }else{
            review.setReviewByInfoFrom(null);
            review.setPatientId(null);
            review.setPatientIdLocal(null);
        }

        review.setConclusionAns(getReviewRequestDto().getConclusionAns());
        if(reviewRequestDto.getEmployeeId().getId() != null){
            review.setEmployeeId(reviewRequestDto.getEmployeeId());
        }else{
            review.setEmployeeId(null);
        }

        if(reviewRequestDto.getTemplateDepartmentId().getId() != null){
            review.setTemplateDepartmentId(reviewRequestDto.getTemplateDepartmentId());
        }else{
            review.setTemplateDepartmentId(null);
        }
        review.setTemplateId(reviewRequestDto.getTemplateId());
        review.setTemplateAvgRating(Math.round((float) totalAnswerRating / totalQuestions));
        review.setCreatedBy(reviewRequestDto.getCreatedBy());
        review.setCreatedDate(LocalDateTime.now());
        review.setLastModifiedBy(reviewRequestDto.getCreatedBy());
        review.setLastModifiedDate(LocalDateTime.now());

        Review saveReview = reviewRepository.save(review);

        reviewRequestDto.getQuestionsReviews().forEach(data->{
            ReviewDetails reviewDetails = new ReviewDetails();
            reviewDetails.setReview(saveReview);
            reviewDetails.setTemplateId(reviewRequestDto.getTemplateId());
            reviewDetails.setQuestionId(data.getQuestionId());
            if(data.getQuestionDeptId().getId() != null)
                reviewDetails.setQuestionDeptId(getDepartmentById(data.getQuestionDeptId().getId()));
            else
                reviewDetails.setQuestionDeptId(null);
            reviewDetails.setOpdIpd(reviewRequestDto.getOpdIpd());
            reviewDetails.setAnswerRating(data.getAnswerRating());
            reviewDetailsRepository.save(reviewDetails);
        });
        response.responseMethod(HttpStatus.OK.value(), "Review saved successfully", null, null);
        return ResponseEntity.ok(response);
    }

    public void saveDepartmentReviews(ReviewRequestDto reviewRequestDto) {
        System.out.println("In saveDepartment");
        Map<Long, DepartmentStats> departmentStatsMap = getLongDepartmentStatsMap(reviewRequestDto);

        // Iterate through the departmentStatsMap to calculate and save the average rating for each department
        for (Map.Entry<Long, DepartmentStats> entry : departmentStatsMap.entrySet()) {
            Long departmentId = entry.getKey();
            DepartmentStats departmentStats = entry.getValue();

            // Check if there is more than one entry for the current department
            if (departmentStats.getQuestionCount() > 0) {
                // Calculate the average rating for the current department
                double avgRating = departmentStats.calculateAvgRating();

                // Save the DepartmentWiseAvgRating entity for the current department
                DepartmentWiseAvgRating avgRatingEntity = new DepartmentWiseAvgRating();
                avgRatingEntity.setDepartmentId(getDepartmentById(departmentId));
                avgRatingEntity.setAvgRating(Math.round(avgRating));
                avgRatingEntity.setTemplateId(reviewRequestDto.getTemplateId());
                avgRatingEntity.setReviewById(reviewRequestDto.getCreatedBy());
                departmentWiseAvgRatingRepository.save(avgRatingEntity);
            }
        }
    }

    private static Map<Long, DepartmentStats> getLongDepartmentStatsMap(ReviewRequestDto reviewRequestDto) {
        List<QuestionsReview> questionsReviews = reviewRequestDto.getQuestionsReviews();

        // Create a map to store total rating and total questions for each department
        Map<Long, DepartmentStats> departmentStatsMap = new HashMap<>();

        // Iterate through the questionsReviews to calculate total rating and total questions for each department
        for (QuestionsReview questionsReview : questionsReviews) {
            // Check if questionDeptId is not null
            if (questionsReview.getQuestionDeptId().getId() != null) {
                Long departmentId = questionsReview.getQuestionDeptId().getId();
                int answerRating = questionsReview.getAnswerRating();

                // Get or create DepartmentStats for the current department
                DepartmentStats departmentStats = departmentStatsMap.computeIfAbsent(departmentId, k -> new DepartmentStats());

                // Update total rating and total questions for the current department
                departmentStats.addRating(answerRating);
                departmentStats.addQuestion();
            }

            // Check if questionId is not null
            if (questionsReview.getQuestionDeptId().getId() == null) {
                continue;
            }
        }
        return departmentStatsMap;
    }


    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    @Override
    public ResponseEntity<?> getDepartmentReviewsAvgRatingForDashBoard(String dataFor) {
        var response = new ApiResponse<>();
        List<DepartmentWiseAvgDataForDashBoard> result = departmentWiseAvgRatingRepository.getDepartmentWiseAvgRating(dataFor);
        if(result != null){
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", result, null);
        }else{
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getPatientReviewsAvgRatingForDashBoard() {
        var response = new ApiResponse<>();

        Map<String, Object> finalResult = new HashMap<>();
        List<PatientWiseTotalAvgDataForDashBoard> result = reviewRepository.getPatientReviewsAvgRating();
        if (result != null) {
            int numerator = 0;
            int denominator = 0;

            for (PatientWiseTotalAvgDataForDashBoard data : result) {
                numerator += data.getHighly_satisfied_count() * 5 +
                        data.getSatisfied_count() * 4 +
                        data.getAverage_count() * 3 +
                        data.getPoor_count() * 2 +
                        data.getDissatisfied_count();
                denominator += data.getHighly_satisfied_count() +
                        data.getSatisfied_count() +
                        data.getAverage_count() +
                        data.getPoor_count() +
                        data.getDissatisfied_count();
            }

            // Avoid division by zero
            if (denominator != 0) {
                double avg = (double) numerator / denominator;
                finalResult.put("result", result);
                finalResult.put("totalAvgInOneToFive", avg);
            } else {
                // Handle the case where denominator is zero
                response.responseMethod(HttpStatus.BAD_REQUEST.value(), "Denominator is zero", null, null);
            }
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", finalResult, null);

        } else {
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getStaffReviewsAvgRatingForDashBoard() {
        var response = new ApiResponse<>();
        Map<String, Object> finalResult = new HashMap<>();
        List<StaffWiseTotalAvgDataForDashBoard> result = reviewRepository.getStaffReviewsAvgRating();
        if (result != null) {
            int numerator = 0;
            int denominator = 0;

            for (StaffWiseTotalAvgDataForDashBoard data : result) {
                numerator += data.getHighly_satisfied_count() * 5 +
                        data.getSatisfied_count() * 4 +
                        data.getAverage_count() * 3 +
                        data.getPoor_count() * 2 +
                        data.getDissatisfied_count();
                denominator += data.getHighly_satisfied_count() +
                        data.getSatisfied_count() +
                        data.getAverage_count() +
                        data.getPoor_count() +
                        data.getDissatisfied_count();
            }

            // Avoid division by zero
            if (denominator != 0) {
                double avg = (double) numerator / denominator;
                finalResult.put("result", result);
                finalResult.put("totalAvgInOneToFive", avg);
            } else {
                // Handle the case where denominator is zero
                response.responseMethod(HttpStatus.BAD_REQUEST.value(), "Denominator is zero", null, null);
            }
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", finalResult, null);

        } else {
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getOpdPatientAvgDateWiseRatingForDashBoard() {
        var response = new ApiResponse<>();
        List<OpdIPdPatientAvgDataForDashBoard> result = reviewRepository.getOpdPatientAvgDateWiseRating();
        if(result != null){
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", result, null);
        }else{
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getIpdPatientAvgDateWiseRatingForDashBoard() {
        var response = new ApiResponse<>();
        List<OpdIPdPatientAvgDataForDashBoard> result = reviewRepository.getIpdPatientAvgDateWiseRating();
        if(result != null){
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", result, null);
        }else{
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getPatientOrEmployeeInformationAutoComplete(Integer getDataFrom, String searchString) {
        var response = new ApiResponse<>();
        List<GetUserInfo> result = feedBackUserInfoRepository.getUserInformation(getDataFrom, searchString);
        if(result != null){
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", result, null);
        }else{
            response.responseMethod(HttpStatus.OK.value(), "Data not found for this role", null, null);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> saveEmployee(Employee employee) {
        var response = new ApiResponse<>();

        Employee emp = new Employee();
        emp.setName(employee.getName());
        employeeRepository.save(emp);
        response.responseMethod(HttpStatus.OK.value(), "Saved Employee", null, null);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getAllEmployess() {
        var response = new ApiResponse<>();
        return  null;
    }
}
