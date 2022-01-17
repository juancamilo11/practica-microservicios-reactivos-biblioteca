package dev.j3c.mspractice.usecases.interfaces;

@FunctionalInterface
public interface GetApprovedSaleById {
    Flux<> apply(String id);
}
