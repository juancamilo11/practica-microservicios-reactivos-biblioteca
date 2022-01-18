package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.usecases.interfaces.GetAllLibraryItemsForLoaning;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllLibraryItemsForLoaningUsecase implements GetAllLibraryItemsForLoaning {


    @Override
    public Flux<LibraryItemForLoanDto> get() {
        return null;
    }
}
