package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.InvoiceNewStock;
import dev.j3c.mspractice.collection.helpers.EnumItemFormat;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.InvoiceNewStockDto;
import dev.j3c.mspractice.dto.helpers.EnumItemFormatDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InvoiceStockMapper {

    public Function<InvoiceNewStockDto, InvoiceNewStock> mapFromDtoToEntity() {
        return (InvoiceNewStockDto invoiceNewStockDto) -> InvoiceNewStock.builder()
                .id(invoiceNewStockDto.getId())
                .date(invoiceNewStockDto.getDate())
                .itemsList(invoiceNewStockDto.getItemsList()
                        .stream()
                        .map(libraryItemDto -> LibraryItem
                                .builder()
                                .id(libraryItemDto.getId())
                                .name(libraryItemDto.getName())
                                .author(libraryItemDto.getAuthor())
                                .format(EnumItemFormat
                                        .valueOf(libraryItemDto.getFormat().getType()))
                                .purchasePrice(libraryItemDto.getPurchasePrice())
                                .build())
                        .collect(Collectors.toList()))
                .totalPrice(invoiceNewStockDto.getTotalPrice()).build();
    }

    public Function<InvoiceNewStock, InvoiceNewStockDto> mapFromEntityToDto() {
        return (InvoiceNewStock invoiceNewStock) -> InvoiceNewStockDto.builder()
                .id(invoiceNewStock.getId())
                .date(invoiceNewStock.getDate())
                .itemsList(invoiceNewStock.getItemsList()
                        .stream()
                        .map(libraryItem -> LibraryItemDto
                                .builder()
                                .id(libraryItem.getId())
                                .name(libraryItem.getName())
                                .author(libraryItem.getAuthor())
                                .format(EnumItemFormatDto
                                        .valueOf(libraryItem.getFormat().getType()))
                                .purchasePrice(libraryItem.getPurchasePrice())
                                .build())
                        .collect(Collectors.toList()))
                .totalPrice(invoiceNewStock.getTotalPrice()).build();
    }
}
