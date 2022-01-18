package dev.j3c.mspractice.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemDtoForLoanDto extends LibraryItemDto {
    @Min(value = 0)
    private Long loanedUnits;

    @Builder
    public LibraryItemDtoForLoanDto(@NotBlank String id, @NotBlank String name, @NotBlank String author, @NotBlank String format, @Min(value = 0) Double purchasePrice, @Min(value = 1) Long availableUnits, @Min(value = 0) Long loanedUnits) {
        super(id, name, author, format, purchasePrice, availableUnits);
        this.loanedUnits = loanedUnits;
    }
}
