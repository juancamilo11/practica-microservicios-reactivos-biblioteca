package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.LibraryItemForSale;
import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LibraryItemsForSaleMapper {
    public Function<LibraryItemForSale, LibraryItemForSaleDto> mapFromEntityToDto() {
        return (LibraryItemForSale libraryItemForSale) -> LibraryItemForSaleDto.builder()
                .id(libraryItemForSale.getId())
                .name(libraryItemForSale.getName())
                .author(libraryItemForSale.getAuthor())
                .format(libraryItemForSale.getFormat())
                .purchasePrice(libraryItemForSale.getPurchasePrice())
                .availableUnits(libraryItemForSale.getAvailableUnits())
                .salePrice(libraryItemForSale.getSalePrice())
                .build();
    }

    public Function<LibraryItemForSaleDto, LibraryItemForSale> mapFromDtoToEntity() {
        return (LibraryItemForSaleDto libraryItemForSaleDto) -> LibraryItemForSale.builder()
                .id(libraryItemForSaleDto.getId())
                .name(libraryItemForSaleDto.getName())
                .author(libraryItemForSaleDto.getAuthor())
                .format(libraryItemForSaleDto.getFormat())
                .purchasePrice(libraryItemForSaleDto.getPurchasePrice())
                .availableUnits(libraryItemForSaleDto.getAvailableUnits())
                .salePrice(libraryItemForSaleDto.getSalePrice())
                .build();
    }
}
