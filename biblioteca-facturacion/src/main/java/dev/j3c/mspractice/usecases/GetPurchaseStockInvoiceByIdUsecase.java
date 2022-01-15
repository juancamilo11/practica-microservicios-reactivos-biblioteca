package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetPurchaseStockInvoiceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetPurchaseStockInvoiceByIdUsecase implements GetPurchaseStockInvoiceById {
    private final PurchaseStockInvoiceRepository invoicesRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;

    @Autowired
    public GetPurchaseStockInvoiceByIdUsecase(PurchaseStockInvoiceRepository invoicesRepository, PurchaseInvoiceMapper purchaseInvoiceMapper) {
        this.invoicesRepository = invoicesRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
    }

    @Override
    public Mono<PurchaseStockInvoiceDto> apply(String id) {
        return invoicesRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error, la factura con id " + id + " no existe en el sistema.")))
                .map(invoice -> purchaseInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(invoice));
    }
}
