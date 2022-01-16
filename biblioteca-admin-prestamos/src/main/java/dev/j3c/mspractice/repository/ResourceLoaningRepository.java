package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.ResourceLoaning;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceLoaningRepository extends ReactiveCrudRepository<ResourceLoaning, String> {

}
