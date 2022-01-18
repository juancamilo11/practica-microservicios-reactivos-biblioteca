package dev.j3c.mspractice.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemDtoForSaleDto extends LibraryItemDto {
    @Min(value = 0)
    private Double salePrice;

    @Builder
    public LibraryItemDtoForSaleDto(@NotBlank String id, @NotBlank String name, @NotBlank String author, @NotBlank String format, @Min(value = 0) Double purchasePrice, @Min(value = 1) Long availableUnits, @Min(value = 0) Double salePrice) {
        super(id, name, author, format, purchasePrice, availableUnits);
        this.salePrice = salePrice;
    }
}
