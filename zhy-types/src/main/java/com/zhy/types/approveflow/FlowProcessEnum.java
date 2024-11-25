package com.zhy.types.approveflow;

import lombok.Getter;

public enum FlowProcessEnum {
    CERT_APPLY(1, "k001", "请假审批(部门主管)"),
    REBURSEMENT(2, "k002", "请假审批(角色)");

    @Getter
    private final long id;

    @Getter
    private final String key;

    private final String name;

    FlowProcessEnum(int id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public static FlowProcessEnum of(long id) {
        for (FlowProcessEnum process : FlowProcessEnum.values()) {
            if (process.getId() == id) {
                return process;
            }
        }
        return null;
    }

    public static FlowProcessEnum of(String key) {
        for (FlowProcessEnum process : FlowProcessEnum.values()) {
            if (process.getKey().equalsIgnoreCase(key)) {
                return process;
            }
        }
        return null;
    }

}
