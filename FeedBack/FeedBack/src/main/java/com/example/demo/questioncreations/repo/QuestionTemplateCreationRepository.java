package com.example.demo.questioncreations.repo;

import com.example.demo.feedbacktakenform.dto.response.TemplateQuestions;
import com.example.demo.questioncreations.dto.response.FeedBackTemplateResponseList;
import com.example.demo.questioncreations.dto.response.IdLabelValue;
import com.example.demo.questioncreations.dto.response.TemplateListingForQRCode;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionTemplateCreationRepository extends JpaRepository<QuestionsCreationsMaster, Long> {

    @Query(value = "select * from fn_question_template_creation_list()", nativeQuery = true)
    List<FeedBackTemplateResponseList> getQuestionTemplateCreationList();

    @Query(value = "select * from fn_template_name_drop_down_test(?1, ?2, ?3)", nativeQuery = true)
    List<IdLabelValue> getTemplateNameForFeedBackForm(String templateFor, Long depId, String patientType);

    @Query(value = "select * from fn_template_name_drop_down_autocomplete(?1, ?2, ?3, ?4)", nativeQuery = true)
    List<IdLabelValue> getTemplateNameAutoComplete(String templateFor, Long depId, String patientType, String seachString);

    @Query(value = "select * from fn_get_template_questions(?1)", nativeQuery = true)
    List<TemplateQuestions> getTemplateQuestionsById(Long tempId);

    @Query(value = "select * from fn_get_template_list_for_qr_generation(?1, ?2)", nativeQuery = true)
    List<TemplateListingForQRCode> getTemplateListForQRCodeGeneration(Long depId, String searchString);
}
