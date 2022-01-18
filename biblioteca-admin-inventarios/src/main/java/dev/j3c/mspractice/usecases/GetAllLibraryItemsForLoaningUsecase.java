package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllLibraryItemsForLoaning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllLibraryItemsForLoaningUsecase implements GetAllLibraryItemsForLoaning {

    private final LibraryItemForLoanRepository libraryItemForLoanRepository;
    private final LibraryItemsForLoanMapper libraryItemsForLoanMapper;

    @Autowired
    public GetAllLibraryItemsForLoaningUsecase(LibraryItemForLoanRepository libraryItemForLoanRepository, LibraryItemsForLoanMapper libraryItemsForLoanMapper) {
        this.libraryItemForLoanRepository = libraryItemForLoanRepository;
        this.libraryItemsForLoanMapper = libraryItemsForLoanMapper;
    }

    @Override
    public Flux<LibraryItemForLoanDto> get() {
        return this.libraryItemForLoanRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(libraryItemForLoan -> this.libraryItemsForLoanMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForLoan));
    }
}
