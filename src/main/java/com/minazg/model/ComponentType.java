package com.minazg.model;

public enum ComponentType {

    PROJECT("PROJECT"),
    RELEASE("RELEASE"),
    SPRINT("SPRINT"),
    WORKORDER("WORKORDER"),
    REPORT("REPORT");

    String componentType;

    ComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentType() {
        return componentType;
    }

}
