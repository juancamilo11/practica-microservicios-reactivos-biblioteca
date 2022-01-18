package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.LibraryItemForLoan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryItemForLoanRepository extends ReactiveCrudRepository<LibraryItemForLoan, String> {

}
