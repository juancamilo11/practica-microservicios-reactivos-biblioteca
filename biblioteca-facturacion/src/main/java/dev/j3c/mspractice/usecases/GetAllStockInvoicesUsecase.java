package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.StockInvoiceMapper;
import dev.j3c.mspractice.repository.StockInvoicesRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllStockInvoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllStockInvoicesUsecase implements GetAllStockInvoices {

    private final StockInvoicesRepository stockInvoicesRepository;
    private final StockInvoiceMapper stockInvoiceMapper;

    @Autowired
    public GetAllStockInvoicesUsecase(StockInvoicesRepository stockInvoicesRepository, StockInvoiceMapper stockInvoiceMapper) {
        this.stockInvoicesRepository = stockInvoicesRepository;
        this.stockInvoiceMapper = stockInvoiceMapper;
    }

    @Override
    public Flux<StockInvoiceDto> get() {
        return stockInvoicesRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(stockInvoice -> stockInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(stockInvoice));
    }
}
