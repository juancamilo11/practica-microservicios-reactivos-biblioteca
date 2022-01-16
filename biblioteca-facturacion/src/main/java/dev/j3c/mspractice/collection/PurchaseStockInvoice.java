package dev.j3c.mspractice.collection;

import dev.j3c.mspractice.collection.helpers.LibraryItem;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class PurchaseStockInvoice extends StockInvoice {
    @Id
    private String id;
    private String nit;
    private String providerName;

    @Builder
    public PurchaseStockInvoice(LocalDate date, String invoiceType, List<LibraryItem> itemsList, double totalPrice, String id, String nit, String providerName) {
        super(date, invoiceType, itemsList, totalPrice);
        this.id = id;
        this.nit = nit;
        this.providerName = providerName;
    }
}
