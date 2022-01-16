package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllResourceLoans {
    Flux<ResourceLoaningDto> get();
}
