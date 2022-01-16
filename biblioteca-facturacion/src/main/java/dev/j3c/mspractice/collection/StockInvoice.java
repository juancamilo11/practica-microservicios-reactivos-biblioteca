package dev.j3c.mspractice.collection;

import dev.j3c.mspractice.collection.helpers.LibraryItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInvoice {
    private LocalDate date;
    private String invoiceType;
    private List<LibraryItem> itemsList;
    private Double totalPrice;
}
