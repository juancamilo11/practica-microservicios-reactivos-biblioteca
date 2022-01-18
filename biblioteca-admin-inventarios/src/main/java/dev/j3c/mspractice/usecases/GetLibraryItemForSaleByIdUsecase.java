package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import dev.j3c.mspractice.mapper.LibraryItemsForSaleMapper;
import dev.j3c.mspractice.repository.LibraryItemForSaleRepository;
import dev.j3c.mspractice.usecases.interfaces.GetLibraryItemForSaleById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetLibraryItemForSaleByIdUsecase implements GetLibraryItemForSaleById {

    private final LibraryItemForSaleRepository libraryItemForSaleRepository;
    private final LibraryItemsForSaleMapper libraryItemsForSaleMapper;

    @Autowired
    public GetLibraryItemForSaleByIdUsecase(LibraryItemForSaleRepository libraryItemForSaleRepository, LibraryItemsForSaleMapper libraryItemsForSaleMapper) {
        this.libraryItemForSaleRepository = libraryItemForSaleRepository;
        this.libraryItemsForSaleMapper = libraryItemsForSaleMapper;
    }

    @Override
    public Mono<LibraryItemForSaleDto> apply(String id) {
        return this.libraryItemForSaleRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(libraryItemForSale -> this.libraryItemsForSaleMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForSale));
    }
}
