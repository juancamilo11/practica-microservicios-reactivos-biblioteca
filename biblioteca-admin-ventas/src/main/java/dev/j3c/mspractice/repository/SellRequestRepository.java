package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.SellRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellRequestRepository extends ReactiveCrudRepository<SellRequest, String> {

}
