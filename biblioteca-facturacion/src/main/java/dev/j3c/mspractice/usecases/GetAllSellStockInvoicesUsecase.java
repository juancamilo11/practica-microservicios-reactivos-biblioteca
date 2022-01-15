package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllSellStockInvoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllSellStockInvoicesUsecase implements GetAllSellStockInvoices {
    private final SellStockInvoiceRepository sellStockInvoiceRepository;
    private final SellInvoiceMapper sellInvoiceMapper;

    @Autowired
    public GetAllSellStockInvoicesUsecase(SellStockInvoiceRepository sellStockInvoiceRepository, SellInvoiceMapper sellInvoiceMapper) {
        this.sellStockInvoiceRepository = sellStockInvoiceRepository;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    @Override
    public Flux<SellStockInvoiceDto> get() {
        return sellStockInvoiceRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(stockInvoice -> sellInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(stockInvoice));
    }
}
