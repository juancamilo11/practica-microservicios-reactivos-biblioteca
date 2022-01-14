package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.InvoiceStockMapper;
import dev.j3c.mspractice.repository.InvoiceNewStockRepository;
import dev.j3c.mspractice.usecases.interfaces.GetStockInvoiceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetStockInvoiceByIdUsecase implements GetStockInvoiceById {

    private InvoiceNewStockRepository invoicesRepository;
    private InvoiceStockMapper invoiceStockMapper;

    @Autowired
    public GetStockInvoiceByIdUsecase(InvoiceNewStockRepository invoicesRepository, InvoiceStockMapper invoiceStockMapper) {
        this.invoicesRepository = invoicesRepository;
        this.invoiceStockMapper = invoiceStockMapper;
    }

    @Override
    public Mono<StockInvoiceDto> apply(String id) {
        return invoicesRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error, la factura con id " + id + " no existe en el sistema.")))
                .map(invoice -> invoiceStockMapper
                        .mapFromEntityToDto()
                        .apply(invoice));
    }
}
