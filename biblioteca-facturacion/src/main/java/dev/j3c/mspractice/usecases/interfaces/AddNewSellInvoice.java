package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface AddNewSellInvoice {
    Mono<SellStockInvoiceDto> apply(@Valid SellStockInvoiceDto sellStockInvoiceDto);
}
