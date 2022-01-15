package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.PurchaseStockInvoice;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PurchaseInvoiceMapper {

    public Function<PurchaseStockInvoiceDto, PurchaseStockInvoice> mapFromDtoToEntity() {
        return (PurchaseStockInvoiceDto purchaseStockInvoiceDto) -> PurchaseStockInvoice.builder()
                .id(purchaseStockInvoiceDto.getId())
                .date(purchaseStockInvoiceDto.getDate())
                .itemsList(purchaseStockInvoiceDto.getItemsList()
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
                .totalPrice(purchaseStockInvoiceDto.getTotalPrice()).build();
    }

    public Function<PurchaseStockInvoice, PurchaseStockInvoiceDto> mapFromEntityToDto() {
        return (PurchaseStockInvoice purchaseStockInvoice) -> PurchaseStockInvoiceDto.builder()
                .id(purchaseStockInvoice.getId())
                .date(purchaseStockInvoice.getDate())
                .itemsList(purchaseStockInvoice.getItemsList()
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
                .totalPrice(purchaseStockInvoice.getTotalPrice()).build();
    }

}
