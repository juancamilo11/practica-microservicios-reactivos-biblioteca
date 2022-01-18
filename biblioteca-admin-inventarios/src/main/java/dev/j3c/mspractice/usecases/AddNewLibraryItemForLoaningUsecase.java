package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.usecases.interfaces.AddNewLibraryItemForLoaning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewLibraryItemForLoaningUsecase implements AddNewLibraryItemForLoaning {

    private final LibraryItemForLoanRepository libraryItemForLoanRepository;
    private final LibraryItemsForLoanMapper libraryItemsForLoanMapper;

    @Autowired
    public AddNewLibraryItemForLoaningUsecase(LibraryItemForLoanRepository libraryItemForLoanRepository, LibraryItemsForLoanMapper libraryItemsForLoanMapper) {
        this.libraryItemForLoanRepository = libraryItemForLoanRepository;
        this.libraryItemsForLoanMapper = libraryItemsForLoanMapper;
    }

    @Override
    public Mono<LibraryItemForLoanDto> apply(LibraryItemForLoanDto libraryItemForLoanDto) {
        return this.libraryItemForLoanRepository
                .save(this.libraryItemsForLoanMapper
                        .mapFromDtoToEntity()
                        .apply(libraryItemForLoanDto))
                .map(libraryItemForLoan -> this.libraryItemsForLoanMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForLoan));
    }
}
