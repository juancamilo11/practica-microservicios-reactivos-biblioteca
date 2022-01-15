package dev.j3c.mspractice.collection.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LibraryItem {
    private String id;
    private String name;
    private String author;
    private String format;
    private Double purchasePrice;
}
