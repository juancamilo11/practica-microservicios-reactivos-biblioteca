package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.SellRequestDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface ReceiveSellRequest {
    Mono<SellRequestDto> apply(@Valid SellRequestDto sellRequestDto);
}
