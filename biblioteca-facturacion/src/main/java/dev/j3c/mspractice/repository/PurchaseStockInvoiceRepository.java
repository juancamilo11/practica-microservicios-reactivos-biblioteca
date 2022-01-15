package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.PurchaseStockInvoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseStockInvoiceRepository extends ReactiveCrudRepository<PurchaseStockInvoice, String> {

}



