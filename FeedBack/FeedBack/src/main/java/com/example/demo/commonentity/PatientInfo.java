package com.example.demo.commonentity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "patient_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //patient details
    @NotNull(message = "mobile number can not be null")
    @Column(length = 14)
    private String mobileNumber;

    @NotNull(message = "uh id can not be null")
    private String UHID;

    @Column(length = 100)
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "mobile_isd_id")
//    private Isd mobileISD;

    @NotNull(message = "registration date can not be null")
    private LocalDate registrationDate;

//    @NotNull(message = "prefix can not be null")
//    @ManyToOne
//    @JoinColumn(name = "prefix_id")
//    private Prefix prefix;

    @NotNull(message = "first name can not be null")
    @Column(length = 100)
//    @Convert(converter = DBField.class)
    private String firstName;

    @Column(length = 100)
//    @Convert(converter = DBField.class)
    private String middleName;

    @NotNull(message = "last name can not be null")
    @Column(length = 100)
//    @Convert(converter = DBField.class)
    private String lastName;

    private LocalDate dob;

//    @ManyToOne
//    @JoinColumn(name = "gender_id")
//    @NotNull(message = "Gender can not be null")
//    private Gender gender;

    @Column(length = 10)
    private String bloodGroup;

    @Column(length = 20)
    private String maritalStatus;

//    @ManyToOne
//    @JoinColumn(name = "nationality_id")
//    private Nationality nationality;
//
//    @ManyToOne
//    @JoinColumn(name = "citizen_id_proof")
//    private CitizenIdProof citizenIdProof;

    @Column(length = 100)
    private String identificationDocumentNumber;

    //address details
    @Column(length = 100)
    private String houseFlatNumber;

    @Column(length = 100)
    private String streetAddress;

//    @ManyToOne
//    @JoinColumn(name = "country_id")
//    private Country country;
//
//    @ManyToOne
//    @JoinColumn(name = "state_id")
//    private State state;
//
//    @ManyToOne
//    @JoinColumn(name = "district_id")
//    private District district;
//
//    @ManyToOne
//    @JoinColumn(name = "area_id")
//    private Area area;
//
//    @ManyToOne
//    @JoinColumn(name = "taluka_id")
//    private Taluka taluka;
//
//    @ManyToOne
//    @JoinColumn(name = "city_id")
//    private City city;
//
//    @ManyToOne
//    @JoinColumn(name = "pin_code_id")
//    private PinCode pinCode;

    @Column(length = 100)
    private String nameOfRepresentative;

    @Column(length = 14)
    private String mobileNumberOfRepresentative;

    @Column(length = 50)
    private String relationshipWithPatient;

//    @ManyToOne
//    @JoinColumn(name = "unit_id")
//    private Unit unit;

    private String patientImagePath;

//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonb")
//    private List<PatientDocuments> patientDocuments;
//
//    @Column(columnDefinition = "jsonb")
//    @Type(type = "jsonb")
//    private List<PatientDocuments> patientRelativeDocument;
}
