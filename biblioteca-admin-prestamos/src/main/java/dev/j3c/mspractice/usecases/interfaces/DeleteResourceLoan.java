package dev.j3c.mspractice.usecases.interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteResourceLoan {
    Mono<Void> accept(String id);
}
