package dev.j3c.mspractice.dto;

import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class SellStockInvoiceDto extends StockInvoiceDto {
    private String clientId;
    private String providerName;

    @Builder
    public SellStockInvoiceDto(@NotBlank String id, @NotNull LocalDate date, @NotEmpty List<LibraryItemDto> itemsList, @Null double totalPrice, String clientId, String providerName) {
        super(id, date, itemsList, totalPrice);
        this.clientId = clientId;
        this.providerName = providerName;
    }
}
