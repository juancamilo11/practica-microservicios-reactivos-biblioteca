package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllPurchaseStockInvoices {
    Flux<StockInvoiceDto> get();
}
