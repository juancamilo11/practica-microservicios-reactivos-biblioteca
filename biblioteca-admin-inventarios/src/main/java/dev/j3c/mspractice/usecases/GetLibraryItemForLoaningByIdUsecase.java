package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.usecases.interfaces.GetLibraryItemForLoaningById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetLibraryItemForLoaningByIdUsecase implements GetLibraryItemForLoaningById {

    private final LibraryItemForLoanRepository libraryItemForLoanRepository;
    private final LibraryItemsForLoanMapper libraryItemsForLoanMapper;

    @Autowired
    public GetLibraryItemForLoaningByIdUsecase(LibraryItemForLoanRepository libraryItemForLoanRepository, LibraryItemsForLoanMapper libraryItemsForLoanMapper) {
        this.libraryItemForLoanRepository = libraryItemForLoanRepository;
        this.libraryItemsForLoanMapper = libraryItemsForLoanMapper;
    }

    @Override
    public Mono<LibraryItemForLoanDto> apply(String id) {
        return this.libraryItemForLoanRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(libraryItemForLoan -> this.libraryItemsForLoanMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForLoan));
    }
}
