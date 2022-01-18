package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import dev.j3c.mspractice.mapper.LibraryItemsForSaleMapper;
import dev.j3c.mspractice.repository.LibraryItemForSaleRepository;
import dev.j3c.mspractice.usecases.interfaces.AddNewLibraryItemForSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewLibraryItemForSaleUsecase implements AddNewLibraryItemForSale {

    private final LibraryItemForSaleRepository libraryItemForSaleRepository;
    private final LibraryItemsForSaleMapper libraryItemsForSaleMapper;

    @Autowired
    public AddNewLibraryItemForSaleUsecase(LibraryItemForSaleRepository libraryItemForSaleRepository, LibraryItemsForSaleMapper libraryItemsForSaleMapper) {
        this.libraryItemForSaleRepository = libraryItemForSaleRepository;
        this.libraryItemsForSaleMapper = libraryItemsForSaleMapper;
    }

    @Override
    public Mono<LibraryItemForSaleDto> apply(LibraryItemForSaleDto libraryItemForSaleDto) {
        return this.libraryItemForSaleRepository
                .save(this.libraryItemsForSaleMapper
                        .mapFromDtoToEntity()
                        .apply(libraryItemForSaleDto))
                .map(libraryItemForSale -> this.libraryItemsForSaleMapper
                        .mapFromEntityToDto()
                        .apply(libraryItemForSale));
    }
}
