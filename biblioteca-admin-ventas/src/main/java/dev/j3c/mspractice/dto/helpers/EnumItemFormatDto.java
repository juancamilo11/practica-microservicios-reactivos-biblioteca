package dev.j3c.mspractice.dto.helpers;


import java.util.Arrays;

public enum EnumItemFormatDto {
    BOOK("Book"),
    ARTICLE("Article"),
    NEWSPAPER("Newspaper"),
    CD("Cd"),
    MAGAZINE("Magazine");

    private final String type;

    EnumItemFormatDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public EnumItemFormatDto getEnumByStringType(String type) {
        return EnumItemFormatDto.valueOf(type);
    }

    public static boolean enumValueIsValid(String type) {
        return Arrays
                .stream(EnumItemFormatDto.values())
                .anyMatch(enumValue -> enumValue.getType().equals(type));
    }
}
