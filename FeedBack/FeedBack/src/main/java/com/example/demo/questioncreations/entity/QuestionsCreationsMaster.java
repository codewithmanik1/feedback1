package com.example.demo.questioncreations.entity;
import com.example.demo.commonentity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mt_feedback_question_creation")
public class QuestionsCreationsMaster extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Role id -----> Consultant, Staff, patient , etc. which means this template will bind to the that Role
    @Column(name = "questions_set_for")
    private String questionSetFor;

    @Column(name = "template_name", unique = true)
    @Length(max = 100)
    private String templateName;

    @Column(name = "question_set_heading")
    @Length(max = 500)
    private String questionSetHeading;

    @Column(name = "question_set_footnote")
    @Length(max = 500)
    private String questionSetFootNote;

    @Column(name = "template_validity_duration_days")
    private Long templateDurationInDays;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @ManyToOne()
    @JoinColumn(name = "mt_answer_pattern_id")
    @NotNull(message = "Answer pattern cannot be null")
    private AnswerPattern answerPatternScaleId;   // 1 : button , 2 :scale

    @Column(name = "questions", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<SaveQuestions> questions;

    @Column(name = "has_conclusion_questions")
    private Boolean hasConclusionQues;

    @Column(name = "conclusion_question_answers", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private ConclusionQuestion conclusionQueAnswers;

    @Column(name = "patient_type")
    private Character patientType;        // 1: OPD , 2: IPD

    @Column(name = "is_general")
    private Boolean isGeneral;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

}
