package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
