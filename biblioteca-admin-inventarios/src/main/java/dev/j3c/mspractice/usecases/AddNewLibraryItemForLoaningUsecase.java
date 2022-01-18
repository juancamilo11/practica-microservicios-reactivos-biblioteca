package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.usecases.interfaces.AddNewLibraryItemForLoaning;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewLibraryItemForLoaningUsecase implements AddNewLibraryItemForLoaning {

    @Override
    public Mono<LibraryItemForLoanDto> apply(LibraryItemForLoanDto libraryItemForLoanDto) {
        return null;
    }
}
