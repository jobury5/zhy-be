package com.zhy.types.aliyun;

import lombok.Getter;

public enum OcrTemplate {
    SFID(35971L, null,"海员服务簿", 1),
    COC(35972L, 35975L,"海员适任证书", 1),
    SMPP(35973L, null, "海员护照", 1),
    HC(35968L, null, "海员健康证", 1),
    STCW(35974L, 35976L, "培训合格证", 1),
    SFID_NEW(31850L, null,"海员服务簿NEW", 2)
    ;

    @Getter
    private final Long templateId;

    @Getter
    private final Long secondTemplateId;

    //1-kv模板 2-表格模板 3-单证票据信息抽取
    @Getter
    private final int type;

    @Getter
    private final String name;

    OcrTemplate(Long templateId, Long secondTemplateId, String name, int type) {
        this.templateId = templateId;
        this.secondTemplateId = secondTemplateId;
        this.name = name;
        this.type = type;
    }

    public static OcrTemplate of(Long templateId) {
        for (OcrTemplate template : OcrTemplate.values()) {
            if (template.getTemplateId().equals(templateId)) {
                return template;
            }
        }
        return null;
    }




}
