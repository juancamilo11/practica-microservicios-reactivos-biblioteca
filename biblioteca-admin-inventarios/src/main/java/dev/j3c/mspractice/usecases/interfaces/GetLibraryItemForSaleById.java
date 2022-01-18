package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GetLibraryItemForSaleById {
    Mono<LibraryItemForSaleDto> apply(String id);
}
