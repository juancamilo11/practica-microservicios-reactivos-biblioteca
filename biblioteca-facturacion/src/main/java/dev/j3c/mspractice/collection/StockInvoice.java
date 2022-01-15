package dev.j3c.mspractice.collection;

import dev.j3c.mspractice.collection.helpers.LibraryItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public abstract class StockInvoice {
    private LocalDate date;
    private List<LibraryItem> itemsList;
    private double totalPrice;
}
