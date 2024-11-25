package com.zhy.types.aliyun;

import lombok.Getter;

public enum InvoiceType {
    INVOICE("invoice", "增值税发票"),
    QUOTA_INVOICE("quota_invoice", "定额发票"),
    COMMON_PRINTED_INVOICE("common_printed_invoice", "通用机打发票"),
    TRAIN_TICKET("train_ticket", "火车票"),
    TAXI_TICKET("taxi_ticket", "出租车票"),
    AIR_ITINERARY("air_itinerary", "机票行程单"),
    BUS_SHIP_TICKET("bus_ship_ticket", "客运车船票"),
    ONLINE_TAXI_ITINERARY("online_taxi_itinerary", "网约车行程单"),
    TAX_CLEARANCE_CERTIFICATE("tax_clearance_certificate", "税收完税证明"),
    CAR_INVOICE("car_invoice", "机动车销售统一发票"),
    ROLL_TICKET("roll_ticket", "卷票"),
    BANK_ACCEPTANCE("bank_acceptance", "银行承兑汇票"),
    TOLL_INVOICE("toll_invoice", "过路过桥费发票"),
    HOTEL_CONSUME("hotel_consume", "旅馆消费单"),
    ;

    @Getter
    private final String invoiceType;

    @Getter
    private final String invoiceTypeCn;

    InvoiceType(String invoiceType, String invoiceTypeCn) {
        this.invoiceType = invoiceType;
        this.invoiceTypeCn = invoiceTypeCn;
    }

    public static InvoiceType of(String invoiceType) {
        for (InvoiceType type : InvoiceType.values()) {
            if (type.getInvoiceType().equalsIgnoreCase(invoiceType)) {
                return type;
            }
        }
        return null;
    }

}
