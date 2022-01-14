package dev.j3c.mspractice.dto.helpers;

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
}
