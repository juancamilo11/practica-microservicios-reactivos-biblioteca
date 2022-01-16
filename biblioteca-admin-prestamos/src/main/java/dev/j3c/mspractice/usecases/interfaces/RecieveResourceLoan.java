package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface RecieveResourceLoan {
    Mono<ResourceLoaningDto> apply(ResourceLoaningDto resourceLoaningDto);
}
