package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.SellStockInvoice;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SellInvoiceMapper {

    public Function<SellStockInvoiceDto, SellStockInvoice> mapFromDtoToEntity() {
        return (SellStockInvoiceDto sellStockInvoiceDto) -> SellStockInvoice.builder()
                .id(sellStockInvoiceDto.getId())
                .date(sellStockInvoiceDto.getDate())
                .itemsList(sellStockInvoiceDto.getItemsList()
                        .stream()
                        .map(libraryItemDto -> LibraryItem
                                .builder()
                                .id(libraryItemDto.getId())
                                .name(libraryItemDto.getName())
                                .author(libraryItemDto.getAuthor())
                                .format(libraryItemDto.getFormat())
                                .purchasePrice(libraryItemDto.getPurchasePrice())
                                .build())
                        .collect(Collectors.toList()))
                .totalPrice(sellStockInvoiceDto.getTotalPrice()).build();
    }

    public Function<SellStockInvoice, SellStockInvoiceDto> mapFromEntityToDto() {
        return (SellStockInvoice sellStockInvoice) -> SellStockInvoiceDto.builder()
                .id(sellStockInvoice.getId())
                .date(sellStockInvoice.getDate())
                .itemsList(sellStockInvoice.getItemsList()
                        .stream()
                        .map(libraryItem -> LibraryItemDto
                                .builder()
                                .id(libraryItem.getId())
                                .name(libraryItem.getName())
                                .author(libraryItem.getAuthor())
                                .format(libraryItem.getFormat())
                                .purchasePrice(libraryItem.getPurchasePrice())
                                .build())
                        .collect(Collectors.toList()))
                .totalPrice(sellStockInvoice.getTotalPrice()).build();
    }
}
