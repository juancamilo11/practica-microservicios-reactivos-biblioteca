package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import reactor.core.publisher.Mono;

public interface GetProvidedResourceById {
    Mono<ResourceLoaningDto> apply(String id);
}
