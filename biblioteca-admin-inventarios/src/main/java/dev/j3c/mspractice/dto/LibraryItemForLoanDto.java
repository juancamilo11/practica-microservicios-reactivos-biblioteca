package dev.j3c.mspractice.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemForLoanDto extends LibraryItemDto {
    @NotNull
    @Min(value = 0)
    private Long loanedUnits;

    @Builder
    public LibraryItemForLoanDto(@NotBlank String id, @NotBlank String name, @NotBlank String author, @NotBlank String format, @Min(value = 0) Double purchasePrice, @Min(value = 1) Long availableUnits, @NotNull @Min(value = 0) Long loanedUnits) {
        super(id, name, author, format, purchasePrice, availableUnits);
        this.loanedUnits = loanedUnits;
    }
}
