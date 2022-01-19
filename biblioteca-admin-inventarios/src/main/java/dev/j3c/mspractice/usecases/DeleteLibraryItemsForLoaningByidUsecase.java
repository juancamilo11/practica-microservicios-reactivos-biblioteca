package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.usecases.interfaces.DeleteLibraryItemsForLoaningByid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteLibraryItemsForLoaningByidUsecase implements DeleteLibraryItemsForLoaningByid {

    private final LibraryItemForLoanRepository libraryItemForLoanRepository;
    private final LibraryItemsForLoanMapper libraryItemsForLoanMapper;

    @Autowired
    public DeleteLibraryItemsForLoaningByidUsecase(LibraryItemForLoanRepository libraryItemForLoanRepository, LibraryItemsForLoanMapper libraryItemsForLoanMapper) {
        this.libraryItemForLoanRepository = libraryItemForLoanRepository;
        this.libraryItemsForLoanMapper = libraryItemsForLoanMapper;
    }

    @Override
    public Mono<Void> accept(String id) {
        return this.libraryItemForLoanRepository
                .deleteById(id)
                .then();
    }
}
