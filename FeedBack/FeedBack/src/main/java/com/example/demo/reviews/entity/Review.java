package com.example.demo.reviews.entity;

import com.example.demo.commonentity.BaseEntity;
import com.example.demo.commonentity.Employee;
import com.example.demo.commonentity.PatientInfo;
import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "review")
@Entity
public class Review extends BaseEntity {

    /******* Entity One ********/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private PatientInfo patientId;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "template_department_id")
    private Department templateDepartmentId;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private QuestionsCreationsMaster templateId;

    @Column(name = "template_avg_rating")
    private Integer templateAvgRating;

    @Column(name = "conclusion_ans")
    private String conclusionAns;

    @Column(name = "review_by_info_from")
    private Integer reviewByInfoFrom;

    @ManyToOne
    @JoinColumn(name = "patient_id_local")
    private FeedbackUserInfo patientIdLocal;

}
