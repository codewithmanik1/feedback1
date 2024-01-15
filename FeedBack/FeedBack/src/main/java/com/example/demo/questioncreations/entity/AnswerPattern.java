package com.example.demo.questioncreations.entity;


import com.example.demo.commonentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mt_answer_pattern")
public class AnswerPattern extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_review_type")
    private String answerReviewType;

    @Column(name = "answer_type")
    private String answerType;

    @Column(name = "answer_pattern_scale", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<AnswerPatternReq> answerPatternReq;

    @Column(name = "is_delete")
    private Boolean isDelete = false;
}
