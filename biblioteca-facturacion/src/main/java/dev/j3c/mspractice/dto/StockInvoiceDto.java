package dev.j3c.mspractice.dto;

import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInvoiceDto {
    @NotBlank
    private String id;
    @NotNull
    private LocalDate date;
    @NotEmpty
    private List<LibraryItemDto> itemsList;
    @Null
    private double totalPrice;
}
