package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.StockInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllPurchaseStockInvoices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllPurchasePurchaseStockInvoicesUsecase implements GetAllPurchaseStockInvoices {

    private final PurchaseStockInvoiceRepository purchaseStockInvoiceRepository;
    private final StockInvoiceMapper stockInvoiceMapper;

    @Autowired
    public GetAllPurchasePurchaseStockInvoicesUsecase(PurchaseStockInvoiceRepository purchaseStockInvoiceRepository, StockInvoiceMapper stockInvoiceMapper) {
        this.purchaseStockInvoiceRepository = purchaseStockInvoiceRepository;
        this.stockInvoiceMapper = stockInvoiceMapper;
    }

    @Override
    public Flux<StockInvoiceDto> get() {
        return purchaseStockInvoiceRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(stockInvoice -> stockInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(stockInvoice));
    }
}
