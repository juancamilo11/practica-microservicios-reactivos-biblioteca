package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.PurchaseStockInvoice;
import dev.j3c.mspractice.collection.SellStockInvoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface SellStockInvoiceRepository extends ReactiveCrudRepository<SellStockInvoice, String> {
    Flux<SellStockInvoice> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd);
}
