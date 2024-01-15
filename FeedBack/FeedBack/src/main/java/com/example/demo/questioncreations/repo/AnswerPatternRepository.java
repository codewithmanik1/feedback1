package com.example.demo.questioncreations.repo;

import com.example.demo.questioncreations.dto.response.AnswerPatternResponseList;
import com.example.demo.questioncreations.entity.AnswerPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerPatternRepository extends JpaRepository<AnswerPattern, Long> {

    @Query(value = "select * from fn_answer_pattern_lists()", nativeQuery = true)
    List<AnswerPatternResponseList> answerPatternList();

}
