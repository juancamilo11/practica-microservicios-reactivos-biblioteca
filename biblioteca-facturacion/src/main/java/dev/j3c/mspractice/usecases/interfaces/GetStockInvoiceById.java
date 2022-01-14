package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GetStockInvoiceById {
    Mono<StockInvoiceDto> apply(String id);
}
