package dev.j3c.mspractice.collection;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemForLoan extends LibraryItem {

    private Long loanedUnits;

    @Builder
    public LibraryItemForLoan(String id, String name, String author, String format, Double purchasePrice, Long availableUnits, Long loanedUnits) {
        super(id, name, author, format, purchasePrice, availableUnits);
        this.loanedUnits = loanedUnits;
    }
}
