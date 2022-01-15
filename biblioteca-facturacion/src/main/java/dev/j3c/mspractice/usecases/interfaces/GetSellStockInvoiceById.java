package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GetSellStockInvoiceById {
    Mono<SellStockInvoiceDto> apply(String id);
}
