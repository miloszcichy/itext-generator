package org.example.report.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CellType {
    HEADER("header"),
    LABEL("label"),
    TEXT("text"),
    SELECT("select"),
    TRAFFIC_LIGHT("traffic");

    private final String name;

    CellType(final String name) {
        this.name = name;
    }

    public static CellType fromString(final String type) {
        return Arrays.stream(values()).filter(cellType -> cellType.name.equals(type)).findFirst().orElse(TEXT);
    }
}
