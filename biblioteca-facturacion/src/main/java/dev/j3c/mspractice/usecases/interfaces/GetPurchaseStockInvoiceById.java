package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GetPurchaseStockInvoiceById {
    Mono<PurchaseStockInvoiceDto> apply(String id);
}
