package com.example.test.enums;

public enum FileTypeEnum {

    PNG(".png");

    private String extension;

    FileTypeEnum(String extension) {
        this.extension = extension;
    }
}
