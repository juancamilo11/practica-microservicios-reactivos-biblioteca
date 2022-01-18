package dev.j3c.mspractice.dto;

import dev.j3c.mspractice.dto.helpers.EnumItemFormatDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
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
    @Min(value = 1)
    private Long availableUnits;

    public LibraryItemDto(String id, String name, String author, String format, Double purchasePrice, Long availableUnits) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.format = format;
        this.purchasePrice = purchasePrice;
        this.availableUnits = availableUnits;
        this.validateItemFormat(format);
    }

    private void validateItemFormat(String format) throws IllegalArgumentException {
        if(!EnumItemFormatDto.enumValueIsValid(format)) {
            throw new IllegalArgumentException("El tipo de formato del recurso no es válido");
        }
    }
}
