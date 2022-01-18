package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.usecases.interfaces.GetLibraryItemForLoaningById;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetLibraryItemForLoaningByIdUsecase implements GetLibraryItemForLoaningById {

    @Override
    public Mono<LibraryItemForLoanDto> apply(String id) {
        return null;
    }
}
