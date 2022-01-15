package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.collection.PurchaseStockInvoice;
import dev.j3c.mspractice.collection.SellStockInvoice;
import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetStockInvoicesBetweenDates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Service
@Validated

public class GetStockInvoicesBetweenDatesUsecase implements GetStockInvoicesBetweenDates {
    private final PurchaseStockInvoiceRepository purchaseInvoiceRepository;
    private final SellStockInvoiceRepository sellStockInvoiceRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;
    private final SellInvoiceMapper sellInvoiceMapper;

    @Autowired
    public GetStockInvoicesBetweenDatesUsecase(PurchaseStockInvoiceRepository purchaseInvoiceRepository, SellStockInvoiceRepository sellStockInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper, SellInvoiceMapper sellInvoiceMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.sellStockInvoiceRepository = sellStockInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    @Override
    public Flux<StockInvoiceDto> apply(LocalDate dateStart, LocalDate dateEnd) {
        return Flux.concat(purchaseInvoiceRepository
                .findAllByDateBetween(dateStart, dateEnd)
                        .map(invoice -> purchaseInvoiceMapper
                                .mapFromEntityToDto()
                                .apply(invoice)),
                        sellStockInvoiceRepository
                                .findAllByDateBetween(dateStart, dateEnd)
                                .map(invoice -> sellInvoiceMapper
                                        .mapFromEntityToDto()
                                        .apply(invoice)));
    }
}
