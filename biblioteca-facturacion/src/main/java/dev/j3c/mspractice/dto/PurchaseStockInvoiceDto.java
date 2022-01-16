package dev.j3c.mspractice.dto;

import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class PurchaseStockInvoiceDto extends StockInvoiceDto {

    @NotBlank
    private String nit;
    @NotBlank
    private String providerName;

    @Builder
    public PurchaseStockInvoiceDto(@NotBlank String id, String invoiceType, @NotNull LocalDate date, @NotEmpty List<LibraryItemDto> itemsList, double totalPrice, String nit, String providerName) {
        super(id, invoiceType, date, itemsList, totalPrice);
        this.nit = nit;
        this.providerName = providerName;
    }
}
