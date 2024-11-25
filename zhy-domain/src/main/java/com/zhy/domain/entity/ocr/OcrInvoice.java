package com.zhy.domain.entity.ocr;

import com.zhy.types.aliyun.InvoiceType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: jobury
 * @Date: 2024/9/13 14:40
 */

@Data
public class OcrInvoice {

    private InvoiceType invoiceType;

    private String invoiceNumber;

    private BigDecimal totalAmount;

    private LocalDate invoiceDate;

    private String remark;

}
