package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.usecases.interfaces.DeleteLibraryItemsForSaleByid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteLibraryItemsForSaleByidUsecase implements DeleteLibraryItemsForSaleByid {

    @Override
    public Mono<Void> accept(String id) {
        return null;
    }
}
