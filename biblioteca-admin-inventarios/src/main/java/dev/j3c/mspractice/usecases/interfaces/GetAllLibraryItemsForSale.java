package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllLibraryItemsForSale {
    Flux<LibraryItemForSaleDto> get();
}
