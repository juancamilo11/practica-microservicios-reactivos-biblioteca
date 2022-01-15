package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface AddNewPurchaseInvoice {
    Mono<PurchaseStockInvoiceDto> apply(@Valid PurchaseStockInvoiceDto purchaseStockInvoiceDto);
}
