package com.zhy.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zhy.constant.BizConstant;
import com.zhy.domain.entity.ocr.OcrCert;
import com.zhy.domain.entity.ocr.OcrCertItem;
import com.zhy.domain.entity.ocr.OcrInvoice;
import com.zhy.types.aliyun.InvoiceType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.zhy.constant.BizConstant.STATUS_CODE_SUCCESS;


/**
 * @Author: jobury
 * @Date: 2024/9/13 17:49
 */

public class OcrParseUtil {

    public static void parseCertData(String jsonString, OcrCert ocrCert, List<OcrCertItem> ocrCertItemList, boolean containTableHeader) {
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonString);
        Integer statusCode = jsonObject.getInteger("statusCode");
        if (statusCode.equals(STATUS_CODE_SUCCESS)) {
            JSONObject body = (JSONObject) jsonObject.get("body");
            String bodyCode = body.getString("code");
            if (bodyCode.equals(String.valueOf(STATUS_CODE_SUCCESS))) {
                String jsonStr = body.getString("data");
                JSONObject data = (JSONObject) JSON.parse(jsonStr);
                if (data != null) {
                    JSONArray ocrDatas = data.getJSONArray("data");
                    // 遍历JSONArray
                    for (int i = 0; i < ocrDatas.size(); i++) {
                        JSONObject obj = ocrDatas.getJSONObject(i);
                        String fieldName = obj.getString("fieldName");
                        if (StrUtil.isBlank(fieldName)) {
                            fieldName = obj.getString("name");
                        }
                        if (StrUtil.isNotBlank(fieldName)) {
                            switch (fieldName) {
                                case BizConstant.CertOcrField.CERT_NUMBER:
                                    ocrCert.setCertNumber(obj.getString("fieldWord"));
                                    break;
                                case BizConstant.CertOcrField.AUTHORITY:
                                    ocrCert.setAuthority(obj.getString("fieldWord"));
                                    break;
                                case BizConstant.CertOcrField.ISSUE_DATE:
                                    String issueDateRaw = obj.getString("fieldWordRaw");
                                    ocrCert.setIssueDateRaw(issueDateRaw);
                                    try {
                                        ocrCert.setIssueDate(LocalDateTimeUtil.parseDate(obj.getString("fieldWord")));
                                    } catch (Exception e) {
                                        ocrCert.setIssueDate(ZhyDateUtil.toLocalDate(issueDateRaw));
                                        //ocrCert.setIssueDate(ZhyDateUtil.toLocalDate("OCT102022"));
                                    }
                                    break;
                                case BizConstant.CertOcrField.EXPIRE_DATE:
                                    String expireDateRaw = obj.getString("fieldWordRaw");
                                    ocrCert.setExpireDateRaw(expireDateRaw);
                                    try {
                                        ocrCert.setExpireDate(LocalDateTimeUtil.parseDate(obj.getString("fieldWord")));
                                    } catch (Exception e) {
                                        ocrCert.setExpireDate(ZhyDateUtil.toLocalDate(expireDateRaw));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    parseCertTable(ocrCertItemList, data, ocrCert, containTableHeader);
                }
            }
        }

    }


    public static void parseCertTable(List<OcrCertItem> ocrCertItemList, JSONObject data, OcrCert ocrCert, boolean containHeader) {
        try {
            //获取所有tables
            JSONArray tables = data.getJSONArray("tables");
            if (null == tables || tables.isEmpty()) {
                return;
            }
            //获取第一个table，假设一个模板只有一个table
            JSONObject table0 = tables.getJSONObject(0);
            //获取table的所有列
            JSONArray columns = table0.getJSONArray("columns");
            List<OcrCertItem> newOcrCertItemList = new ArrayList<>();
            for (int i = 0; i < columns.size(); i++) {
                JSONObject column = columns.getJSONObject(i);
                String colName = column.getString("col_name");
                JSONArray bodies = column.getJSONArray("bodies");
                for (int j = (containHeader ? 1 : 0); j < bodies.size(); j++) {
                    if (i == 0) {
                        newOcrCertItemList.add(new OcrCertItem());
                    }
                    OcrCertItem ocrCertItem = newOcrCertItemList.get((containHeader ? j - 1 : j));
                    JSONObject cell = bodies.getJSONObject(j);
                    switch (colName) {
                        //STCW证书
                        case BizConstant.CertOcrField.PREFIX:
                            ocrCertItem.setPrefix(cell.getString("value"));
                            break;
                        case BizConstant.CertOcrField.TITLE:
                            ocrCertItem.setTitle(cell.getString("value"));
                            break;
                        case BizConstant.CertOcrField.ISSUE_DATE:
                            String issueDate = cell.getString("value");
                            ocrCertItem.setIssueDateRaw(issueDate);
                            try {
                                ocrCertItem.setIssueDate(LocalDateTimeUtil.parseDate(issueDate));
                            } catch (Exception ignored) {
                                ocrCertItem.setIssueDate(ZhyDateUtil.toLocalDate(issueDate));
                            }
                            break;
                        case BizConstant.CertOcrField.EXPIRE_DATE:
                            String expireDate = cell.getString("value");
                            ocrCertItem.setExpireDateRaw(expireDate);
                            try {
                                ocrCertItem.setExpireDate(LocalDateTimeUtil.parseDate(expireDate));
                            } catch (Exception ignored) {
                                ocrCertItem.setExpireDate(ZhyDateUtil.toLocalDate(expireDate));
                            }
                            break;
                        //COC证书
                        case BizConstant.CertOcrField.CAPACITY:
                            if (StrUtil.isBlank(ocrCert.getCapacity())) {
                                ocrCert.setCapacity(cell.getString("value"));
                            }
                            break;
                        case BizConstant.CertOcrField.LIMITATIONS:
                            if (StrUtil.isBlank(ocrCert.getLimitations())) {
                                ocrCert.setLimitations(cell.getString("value"));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if (CollUtil.isNotEmpty(newOcrCertItemList)) {
                ocrCertItemList.addAll(newOcrCertItemList);
            }
        } catch (Exception ignored) {
        }
    }

    public static void parseInvoiceJson(List<OcrInvoice> invoiceList, String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONObject body = jsonObject.getJSONObject("body");
        String data = body.getString("data");
        JSONObject dataObject = JSON.parseObject(data);
        JSONArray subMsgs = dataObject.getJSONArray("subMsgs");
        for (int j = 0; j < subMsgs.size(); j++) {
            JSONObject invoiceObj = subMsgs.getJSONObject(j);
            String invoiceType = invoiceObj.getString("op");
            InvoiceType type = InvoiceType.of(invoiceType);
            if (null == type) {
                continue;
            }
            JSONObject result = invoiceObj.getJSONObject("result");
            if (null == result) {
                continue;
            }
            JSONObject realData = result.getJSONObject("data");
            if (null == realData) {
                continue;
            }
            invoiceList.add(OcrParseUtil.parseInvoiceData(type, realData));
        }
    }

    public static OcrInvoice parseInvoiceData(InvoiceType invoiceType, JSONObject data) {
        OcrInvoice ocrInvoice = new OcrInvoice();
        ocrInvoice.setInvoiceType(invoiceType);
        switch (invoiceType) {
            case INVOICE:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("invoiceDate"), "yyyy年M月d日"));
                ocrInvoice.setRemark(data.getString("remarks"));
                break;
            case QUOTA_INVOICE:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("Amount")));
                ocrInvoice.setRemark(data.getString("title"));
                break;
            case COMMON_PRINTED_INVOICE:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setRemark(data.getString("remarks"));
                break;
            case TRAIN_TICKET:
                ocrInvoice.setInvoiceNumber(data.getString("ticketNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("fare")));
                //departureTime -> 2024年07月08日18:21开
                String departureTime = data.getString("departureTime");
                if (departureTime.length() >= 11) {
                    String departureDate = StrUtil.sub(departureTime, 0, 11);
                    ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(departureDate, "yyyy年MM月dd日"));
                }
                String remarks = StrUtil.concat(true,
                        data.getString("passengerInfo"),
                        departureTime,
                        "从", data.getString("departureStation"),
                        "到", data.getString("arrivalStation"),
                        data.getString("trainNumber"),
                        data.getString("seatType"),
                        data.getString("seatNumber"));
                ocrInvoice.setRemark(remarks);
                break;
            case TAXI_TICKET:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("fare")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("date")));
                ocrInvoice.setRemark(StrUtil.concat(true,
                        data.getString("licensePlateNumber"),
                        "上车",
                        data.getString("pickUpTime"),
                        "下车",
                        data.getString("dropOffTime"),
                        ",",
                        data.getString("mileage"),
                        "公里"));
                break;
            case AIR_ITINERARY:
                ocrInvoice.setInvoiceNumber(data.getString("serialNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("issueDate")));
                ocrInvoice.setRemark(data.getString("issueCompany"));
                break;
            case BUS_SHIP_TICKET:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("date")));
                ocrInvoice.setRemark(StrUtil.concat(true,
                        data.getString("title"),
                        data.getString("passengerName"),
                        data.getString("idcardNo"),
                        "从",
                        data.getString("departureStation"),
                        "到",
                        data.getString("arrivalStation")));
                break;
            case ONLINE_TAXI_ITINERARY:
                ocrInvoice.setInvoiceNumber(null);
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("applicationDate")));
                ocrInvoice.setRemark(data.getString("serviceProvider"));
                break;
            case TAX_CLEARANCE_CERTIFICATE:
                ocrInvoice.setInvoiceNumber(data.getString("taxNumbe"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("issueDate")));
                break;
            case CAR_INVOICE:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("invoiceAmountCn")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("invoiceDate")));
                break;
            case ROLL_TICKET:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("invoiceDate")));
                break;
            case BANK_ACCEPTANCE:
                ocrInvoice.setInvoiceNumber(data.getString("draftNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("issueDate")));
                break;
            case TOLL_INVOICE:
                ocrInvoice.setInvoiceNumber(data.getString("invoiceNumber"));
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalAmount")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("date")));
                ocrInvoice.setRemark(StrUtil.concat(true,
                        data.getString("title"),
                        data.getString("time"),
                        data.getString("vehicleType"),
                        "入口", data.getString("entranceName"),
                        "出口", data.getString("exitName")
                ));
                break;
            case HOTEL_CONSUME:
                ocrInvoice.setInvoiceNumber(null);
                ocrInvoice.setTotalAmount(new BigDecimal(data.getString("totalConsumption")));
                ocrInvoice.setInvoiceDate(LocalDateTimeUtil.parseDate(data.getString("checkInDate")));
                break;
            default:
                break;
        }
        return ocrInvoice;
    }


}
