package com.zhy.domain.entity.ocr;

import lombok.Data;
import java.util.List;
import java.time.LocalDate;

/**
 * @Author: jobury
 * @Date: 2024/9/14 09:23
 */

@Data
public class OcrCert {

    private String certNumber;

    private String authority;

    private String issueDateRaw;

    private LocalDate issueDate;

    private String expireDateRaw;

    private LocalDate expireDate;

    //COC-等级与职务
    private String capacity;

    //COC-适用的限制
    private String limitations;

    private List<OcrCertItem> ocrCertItems;

}
