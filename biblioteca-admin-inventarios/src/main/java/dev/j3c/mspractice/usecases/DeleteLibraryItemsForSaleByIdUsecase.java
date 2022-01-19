package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.mapper.LibraryItemsForSaleMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.repository.LibraryItemForSaleRepository;
import dev.j3c.mspractice.usecases.interfaces.DeleteLibraryItemsForSaleById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteLibraryItemsForSaleByIdUsecase implements DeleteLibraryItemsForSaleById {

    private final LibraryItemForSaleRepository libraryItemForSaleRepository;
    private final LibraryItemsForSaleMapper libraryItemsForSaleMapper;

    @Autowired
    public DeleteLibraryItemsForSaleByIdUsecase(LibraryItemForSaleRepository libraryItemForSaleRepository, LibraryItemsForSaleMapper libraryItemsForSaleMapper) {
        this.libraryItemForSaleRepository = libraryItemForSaleRepository;
        this.libraryItemsForSaleMapper = libraryItemsForSaleMapper;
    }

    @Override
    public Mono<Void> accept(String id) {
        return this.libraryItemForSaleRepository
                .deleteById(id)
                .then();
    }
}
