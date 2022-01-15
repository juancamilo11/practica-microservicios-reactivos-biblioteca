package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.StockInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetPurchaseStockInvoiceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetPurchaseStockInvoiceByIdUsecase implements GetPurchaseStockInvoiceById {

    private PurchaseStockInvoiceRepository invoicesRepository;
    private StockInvoiceMapper stockInvoiceMapper;

    @Autowired
    public GetPurchaseStockInvoiceByIdUsecase(PurchaseStockInvoiceRepository invoicesRepository, StockInvoiceMapper stockInvoiceMapper) {
        this.invoicesRepository = invoicesRepository;
        this.stockInvoiceMapper = stockInvoiceMapper;
    }

    @Override
    public Mono<StockInvoiceDto> apply(String id) {
        return invoicesRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error, la factura con id " + id + " no existe en el sistema.")))
                .map(invoice -> stockInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(invoice));
    }
}
