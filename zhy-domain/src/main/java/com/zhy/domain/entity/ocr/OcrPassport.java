package com.zhy.domain.entity.ocr;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: jobury
 * @Date: 2024/9/13 10:46
 */

@Data
public class OcrPassport {

    private String passportType;

    private String countryCode;

    private String passportNumber;

    private String name;

    private String nameEn;

    private String sex;

    private String birthPlace;

    private String nationality;

    private String issuePlace;

    private String issueAuthority;

    private LocalDate validToDate;

    private LocalDate birthDate;

    private LocalDate issueDate;

}