package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import reactor.core.publisher.Flux;

public interface GetAllReturnedResources {
    Flux<ResourceLoaningDto> get();
}
