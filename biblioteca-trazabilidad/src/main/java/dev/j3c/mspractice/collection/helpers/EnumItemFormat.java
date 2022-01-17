package dev.j3c.mspractice.collection.helpers;

public enum EnumItemFormat {
    BOOK("Book"),
    ARTICLE("Article"),
    NEWSPAPER("Newspaper"),
    CD("Cd"),
    MAGAZINE("Magazine");

    private final String type;

    EnumItemFormat(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public EnumItemFormat getEnumByStringType(String type) {
        return EnumItemFormat.valueOf(type);
    }
}
