package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import dev.j3c.mspractice.mapper.LibraryItemsForSaleMapper;
import dev.j3c.mspractice.repository.LibraryItemForSaleRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllLibraryItemsForSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllLibraryItemsForSaleUsecase implements GetAllLibraryItemsForSale {

    private final LibraryItemForSaleRepository libraryItemForSaleRepository;
    private final LibraryItemsForSaleMapper libraryItemsForSaleMapper;

    @Autowired
    public GetAllLibraryItemsForSaleUsecase(LibraryItemForSaleRepository libraryItemForSaleRepository, LibraryItemsForSaleMapper libraryItemsForSaleMapper) {
        this.libraryItemForSaleRepository = libraryItemForSaleRepository;
        this.libraryItemsForSaleMapper = libraryItemsForSaleMapper;
    }

    @Override
    public Flux<LibraryItemForSaleDto> get() {
        return this.libraryItemForSaleRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(libraryItemForSale -> this.libraryItemsForSaleMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForSale));
    }
}
