package com.zhy.domain.entity.ocr;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: jobury
 * @Date: 2024/9/13 10:47
 */

@Data
public class OcrIdentity {

    private String idNumber;

    private String name;

    private String sex;

    private String ethnicity;

    private LocalDate birthDate;

    private String address;

}
