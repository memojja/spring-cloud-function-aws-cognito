package com.allegory.users.model;

public class NumberStatus {

    private NumberCheck status;

    public NumberStatus(NumberCheck status) {
        this.status = status;
    }

    public NumberCheck getStatus() {
        return status;
    }

    public void setStatus(NumberCheck status) {
        this.status = status;
    }
}
