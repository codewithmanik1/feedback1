package com.example.demo.questioncreations.repo;
import com.example.demo.questioncreations.dto.response.IdLabelValue;
import com.example.demo.questioncreations.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select * from fn_department_dropdown()", nativeQuery = true)
    List<IdLabelValue> getDepartmentDropDownService();

    @Query(value = "SELECT * FROM fn_dept_dropdown_for_feedback_form(:questionSetFor, :patientType)", nativeQuery = true)
    List<IdLabelValue> getDepartmentDropDownForFeedBackForm(@Param("questionSetFor") String questionSetFor, @Param("patientType") String patientType);

}
