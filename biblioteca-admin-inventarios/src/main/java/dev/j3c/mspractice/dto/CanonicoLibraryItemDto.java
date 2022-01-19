package dev.j3c.mspractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CanonicoLibraryItemDto {
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
}
