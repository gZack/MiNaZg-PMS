package com.minazg.model;

public enum StatusType {
    INPROGRESS("INPROGRESS"),
    COMPLETED("COMPLETED"),
    CREATED("CREATED"),
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
