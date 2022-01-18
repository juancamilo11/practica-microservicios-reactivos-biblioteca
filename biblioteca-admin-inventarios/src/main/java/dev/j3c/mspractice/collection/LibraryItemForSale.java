package dev.j3c.mspractice.collection;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemForSale extends LibraryItem {

    private Double salePrice;

    @Builder
    public LibraryItemForSale(String id, String name, String author, String format, Double purchasePrice, Long availableUnits, Double salePrice) {
        super(id, name, author, format, purchasePrice, availableUnits);
        this.salePrice = salePrice;
    }
}
