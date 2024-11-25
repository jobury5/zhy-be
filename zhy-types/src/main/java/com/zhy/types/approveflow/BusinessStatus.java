package com.zhy.types.approveflow;

import java.util.Objects;

public enum BusinessStatus {

    NEW(1),
    PROCESSING(2),
    PASS(3),
    REJECT(4),
    CANCEL(5);

    private final int value;

    BusinessStatus(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean ne(Integer value) {
        return !this.eq(value);
    }

    public boolean eq(Integer value) {
        return Objects.equals(this.value, value);
    }


}
