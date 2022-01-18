package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface AddNewLibraryItemForLoaning {
    Mono<LibraryItemForLoanDto> apply(LibraryItemForLoanDto libraryItemForLoanDto);
}
