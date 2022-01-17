package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import reactor.core.publisher.Mono;

public interface GetReturnedResourceById {
    Mono<ResourceLoaningDto> apply(String id);
}
