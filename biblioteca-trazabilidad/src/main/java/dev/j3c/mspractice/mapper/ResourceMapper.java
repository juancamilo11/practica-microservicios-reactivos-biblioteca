package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.ResourceLoaning;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ResourceMapper {

    public Function<ResourceLoaning, ResourceLoaningDto> mapFromEntityToDto() {
        return (ResourceLoaning resourceLoaning) -> ResourceLoaningDto.builder()
                .id(resourceLoaning.getId())
                .customerId(resourceLoaning.getCustomerId())
                .customerName(resourceLoaning.getCustomerName())
                .itemsList(resourceLoaning.getItemsList().stream().map(libraryItem -> LibraryItemDto.builder()
                        .id(libraryItem.getId())
                        .author(libraryItem.getAuthor())
                        .format(libraryItem.getFormat())
                        .name(libraryItem.getFormat())
                        .purchasePrice(libraryItem.getPurchasePrice())
                        .build()).collect(Collectors.toList()))
                .date(resourceLoaning.getDate())
                .type(resourceLoaning.getType())
                .build();
    }

    public Function<ResourceLoaningDto, ResourceLoaning> mapFromDtoToEntity() {
        return (ResourceLoaningDto resourceLoaningDto) -> ResourceLoaning.builder()
                .id(resourceLoaningDto.getId())
                .customerId(resourceLoaningDto.getCustomerId())
                .customerName(resourceLoaningDto.getCustomerName())
                .itemsList(resourceLoaningDto.getItemsList().stream().map(libraryItem -> LibraryItem.builder()
                        .id(libraryItem.getId())
                        .author(libraryItem.getAuthor())
                        .format(libraryItem.getFormat())
                        .name(libraryItem.getFormat())
                        .purchasePrice(libraryItem.getPurchasePrice())
                        .build()).collect(Collectors.toList()))
                .date(resourceLoaningDto.getDate())
                .type(resourceLoaningDto.getType())
                .build();
    }

}
