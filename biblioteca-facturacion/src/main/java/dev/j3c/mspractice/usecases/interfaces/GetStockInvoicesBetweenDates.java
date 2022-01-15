package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@FunctionalInterface
public interface GetStockInvoicesBetweenDates {
    Flux<StockInvoiceDto> apply(LocalDate dateStart, LocalDate dateEnd);
}
