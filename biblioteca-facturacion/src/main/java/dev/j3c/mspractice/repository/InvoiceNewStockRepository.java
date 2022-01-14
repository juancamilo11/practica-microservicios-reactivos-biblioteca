package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.StockInvoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceNewStockRepository extends ReactiveCrudRepository<StockInvoice, String> {

}



