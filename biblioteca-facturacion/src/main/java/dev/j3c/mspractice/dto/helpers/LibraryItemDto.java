package dev.j3c.mspractice.dto.helpers;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class LibraryItemDto {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @NotBlank
    private String format;
    @Min(value = 0)
    private Double purchasePrice;

    public LibraryItemDto(String id, String name, String author, String format, Double purchasePrice) throws IllegalArgumentException {
        this.id = id;
        this.name = name;
        this.author = author;
        this.format = format;
        this.purchasePrice = purchasePrice;
        this.validateItemFormat(format);
    }

    private void validateItemFormat(String format) throws IllegalArgumentException {
        if(EnumItemFormatDto.enumValueIsValid(format)) {
            throw new IllegalArgumentException("");
        }
    }
}
