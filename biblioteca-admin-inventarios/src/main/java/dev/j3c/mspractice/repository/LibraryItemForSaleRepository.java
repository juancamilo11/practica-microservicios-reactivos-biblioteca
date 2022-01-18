package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.LibraryItemForSale;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LibraryItemForSaleRepository extends ReactiveCrudRepository<LibraryItemForSale, String> {

}
