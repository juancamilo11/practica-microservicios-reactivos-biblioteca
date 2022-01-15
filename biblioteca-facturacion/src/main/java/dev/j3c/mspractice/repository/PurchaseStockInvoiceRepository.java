package dev.j3c.mspractice.repository;

import dev.j3c.mspractice.collection.PurchaseStockInvoice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface PurchaseStockInvoiceRepository extends ReactiveCrudRepository<PurchaseStockInvoice, String> {
    Flux<PurchaseStockInvoice> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd);
    Flux<PurchaseStockInvoice> findAllByTotalPriceBetween(double minPrice, double maxPrice);
}



