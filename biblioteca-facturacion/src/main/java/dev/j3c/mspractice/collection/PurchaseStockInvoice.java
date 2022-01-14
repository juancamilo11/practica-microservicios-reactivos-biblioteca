package dev.j3c.mspractice.collection;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Document
public class PurchaseStockInvoice extends StockInvoice {
    @Id
    private String id;
    private String nit;
    private String providerName;
}
