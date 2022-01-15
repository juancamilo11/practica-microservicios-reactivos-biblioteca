package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.SellStockInvoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellStockInvoiceRepository extends ReactiveCrudRepository<SellStockInvoice, String> {

}
