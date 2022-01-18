package dev.j3c.mspractice.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LibraryItem {
    @Id
    private String id;
    private String name;
    private String author;
    private String format;
    private Double purchasePrice;
    private Long availableUnits;
}
