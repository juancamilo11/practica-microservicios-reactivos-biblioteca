package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface GetAllSellStockInvoices {
    Flux<SellStockInvoiceDto> get();

}
