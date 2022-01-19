package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.CanonicoLibraryItemDto;
import dev.j3c.mspractice.dto.LibraryItemDto;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface VerifyItemsAvailabilityForLoaning {
    Mono<Boolean> apply(Map<String, List<CanonicoLibraryItemDto>> itemsList);
}
