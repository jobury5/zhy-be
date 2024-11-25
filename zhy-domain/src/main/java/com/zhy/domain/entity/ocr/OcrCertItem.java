package com.zhy.domain.entity.ocr;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: jobury
 * @Date: 2024/9/14 09:56
 */

@Data
public class OcrCertItem {

    private String prefix;

    private String title;

    private String issueDateRaw;

    private LocalDate issueDate;

    private String expireDateRaw;

    private LocalDate expireDate;
}
