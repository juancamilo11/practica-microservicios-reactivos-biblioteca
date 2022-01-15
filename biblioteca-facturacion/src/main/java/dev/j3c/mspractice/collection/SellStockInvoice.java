package dev.j3c.mspractice.collection;

import dev.j3c.mspractice.collection.helpers.LibraryItem;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document
public class SellStockInvoice extends StockInvoice {
    @Id
    private String id;
    private String customerId;
    private String providerName;

    @Builder
    public SellStockInvoice(LocalDate date, List<LibraryItem> itemsList, double totalPrice, String id, String customerId, String providerName) {
        super(date, itemsList, totalPrice);
        this.id = id;
        this.customerId = customerId;
        this.providerName = providerName;
    }
}
