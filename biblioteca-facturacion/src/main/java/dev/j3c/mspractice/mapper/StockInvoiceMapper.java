package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.StockInvoice;
import dev.j3c.mspractice.collection.helpers.EnumItemFormat;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.EnumItemFormatDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StockInvoiceMapper {

    public Function<StockInvoiceDto, StockInvoice> mapFromDtoToEntity() {
        return (StockInvoiceDto stockInvoiceDto) -> StockInvoice.builder()
                .id(stockInvoiceDto.getId())
                .date(stockInvoiceDto.getDate())
                .itemsList(stockInvoiceDto.getItemsList()
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
                .totalPrice(stockInvoiceDto.getTotalPrice()).build();
    }

    public Function<StockInvoice, StockInvoiceDto> mapFromEntityToDto() {
        return (StockInvoice stockInvoice) -> StockInvoiceDto.builder()
                .id(stockInvoice.getId())
                .date(stockInvoice.getDate())
                .itemsList(stockInvoice.getItemsList()
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
                .totalPrice(stockInvoice.getTotalPrice()).build();
    }
}
