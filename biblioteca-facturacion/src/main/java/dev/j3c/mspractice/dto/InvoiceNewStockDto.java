package dev.j3c.mspractice.dto;

import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class InvoiceNewStockDto {
    @NotBlank
    private String id;
    @NotNull
    private LocalDate date;
    @NotEmpty
    private List<LibraryItemDto> itemsList;
    @Null
    private double totalPrice;
}
