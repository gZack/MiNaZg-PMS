package com.minazg.model;

public enum  WorkOrderType {
    FEATURE("FEATURE"),
    BUG("BUG");

    private String workOrderType;

    WorkOrderType(String workOrderType){
        this.workOrderType = workOrderType;
    }

    public String getWorkOrderType() {
        return workOrderType;
    }
}
