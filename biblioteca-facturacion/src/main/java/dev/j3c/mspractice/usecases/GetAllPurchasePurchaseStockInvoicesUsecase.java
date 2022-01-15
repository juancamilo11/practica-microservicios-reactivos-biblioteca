package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
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
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;

    @Autowired
    public GetAllPurchasePurchaseStockInvoicesUsecase(PurchaseStockInvoiceRepository purchaseStockInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper) {
        this.purchaseStockInvoiceRepository = purchaseStockInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
    }

    @Override
    public Flux<PurchaseStockInvoiceDto> get() {
        return purchaseStockInvoiceRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(stockInvoice -> purchaseInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(stockInvoice));
    }
}
