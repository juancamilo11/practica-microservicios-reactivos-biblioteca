package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllLibraryItemsForLoaning {
    Flux<LibraryItemForLoanDto> get();
}
