package com.allegory.users.model;

public enum NumberCheck {

    AVAILABLE("available"),
    NOT_AVAILABLE("not available");

    private String status;

    NumberCheck(String status) {
        this.status = status;
    }

    public String value(){
        return status;
    }
}
