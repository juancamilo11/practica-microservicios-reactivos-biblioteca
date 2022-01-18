package dev.j3c.mspractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
