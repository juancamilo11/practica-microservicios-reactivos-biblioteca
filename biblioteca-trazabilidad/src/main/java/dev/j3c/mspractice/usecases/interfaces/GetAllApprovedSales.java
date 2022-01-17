package dev.j3c.mspractice.usecases.interfaces;

@FunctionalInterface
public interface GetAllApprovedSales {
    Flux<> get();
}
