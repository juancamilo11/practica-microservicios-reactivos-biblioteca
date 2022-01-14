package dev.j3c.mspractice.dto.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LibraryItemDto {
    private String id;
    private String name;
    private String author;
    private EnumItemFormatDto format;
    private Double purchasePrice;
}
