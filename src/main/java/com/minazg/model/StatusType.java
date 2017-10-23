package com.minazg.model;

public enum StatusType {
    CREATED("CREATED"),
    INPROGRESS("INPROGRESS"),
    COMPLETED("COMPLETED"),
    PENDING("PENDING"),
    CANCELLED("CANCELLED");

    String statusType;

    StatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }
}
