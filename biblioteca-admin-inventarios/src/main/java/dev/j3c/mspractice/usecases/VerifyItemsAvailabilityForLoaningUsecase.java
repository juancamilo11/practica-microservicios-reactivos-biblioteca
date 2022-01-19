package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.collection.LibraryItem;
import dev.j3c.mspractice.dto.CanonicoLibraryItemDto;
import dev.j3c.mspractice.dto.LibraryItemDto;
import dev.j3c.mspractice.mapper.LibraryItemsForLoanMapper;
import dev.j3c.mspractice.repository.LibraryItemForLoanRepository;
import dev.j3c.mspractice.usecases.interfaces.VerifyItemsAvailabilityForLoaning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class VerifyItemsAvailabilityForLoaningUsecase  implements VerifyItemsAvailabilityForLoaning{

    private final LibraryItemForLoanRepository libraryItemForLoanRepository;

    @Autowired
    public VerifyItemsAvailabilityForLoaningUsecase(LibraryItemForLoanRepository libraryItemForLoanRepository) {
        this.libraryItemForLoanRepository = libraryItemForLoanRepository;
    }

    @Override
    public Mono<Boolean> apply(Map<String, List<CanonicoLibraryItemDto>> itemsGroupedMap) {
        var resultListIdsNoEnoughItems = itemsGroupedMap.keySet().stream().filter(id -> {
            List<CanonicoLibraryItemDto> libraryItems =  itemsGroupedMap.get(id);
            return this.libraryItemForLoanRepository
                    .findById(id)
                    .map(LibraryItem::getAvailableUnits)
                    .blockOptional()
                    .orElse(0L) < libraryItems.size();
        }).collect(Collectors.toList());
        return Mono.just(resultListIdsNoEnoughItems.isEmpty());
    }

}
