package com.example.demo.questioncreations.entity;

import com.example.demo.commonentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mt_qr_code")
@Getter
@Setter
public class QRCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "qr_code_path")
    private String qrCodePath;

    @ManyToOne()
    @JoinColumn(name = "dep_id")
    private Department departmentId;

    @ManyToOne()
    @JoinColumn(name = "template_id")
    private QuestionsCreationsMaster templateId;

    @Column(name = "redirect_link")
    private String redirectLink;
}
