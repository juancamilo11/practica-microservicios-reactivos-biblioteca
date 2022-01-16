package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.SellRequest;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.SellRequestDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SellRequestMapper {

    public Function<SellRequest, SellRequestDto> mapFromEntityToDto() {
        return (SellRequest sellRequest) -> SellRequestDto.builder()
                .id(sellRequest.getId())
                .customerId(sellRequest.getCustomerId())
                .customerName(sellRequest.getCustomerName())
                .itemsList(sellRequest.getItemsList().stream().map(libraryItem -> LibraryItemDto.builder()
                        .id(libraryItem.getId())
                        .author(libraryItem.getAuthor())
                        .format(libraryItem.getFormat())
                        .name(libraryItem.getFormat())
                        .purchasePrice(libraryItem.getPurchasePrice())
                        .build()).collect(Collectors.toList()))
                .date(sellRequest.getDate())
                .build();
    }

    public Function<SellRequestDto, SellRequest> mapFromDtoToEntity() {
        return (SellRequestDto sellRequestDto) -> SellRequest.builder()
                .id(sellRequestDto.getId())
                .customerId(sellRequestDto.getCustomerId())
                .customerName(sellRequestDto.getCustomerName())
                .itemsList(sellRequestDto.getItemsList().stream().map(libraryItem -> LibraryItem.builder()
                        .id(libraryItem.getId())
                        .author(libraryItem.getAuthor())
                        .format(libraryItem.getFormat())
                        .name(libraryItem.getFormat())
                        .purchasePrice(libraryItem.getPurchasePrice())
                        .build()).collect(Collectors.toList()))
                .date(sellRequestDto.getDate())
                .build();
    }
}
