package com.example.demo.reviews.entity;

import com.example.demo.questioncreations.entity.Department;
import com.example.demo.questioncreations.entity.QuestionsCreationsMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "department_wise_avg_rating")
public class DepartmentWiseAvgRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @Column(name = "avg_rating")
    private Long avgRating;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private QuestionsCreationsMaster templateId;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "review_by_id")
    private Long reviewById;
}
