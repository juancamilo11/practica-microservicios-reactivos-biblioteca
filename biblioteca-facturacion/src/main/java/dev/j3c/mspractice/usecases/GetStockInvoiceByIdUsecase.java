package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.StockInvoiceMapper;
import dev.j3c.mspractice.repository.StockInvoicesRepository;
import dev.j3c.mspractice.usecases.interfaces.GetStockInvoiceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetStockInvoiceByIdUsecase implements GetStockInvoiceById {

    private StockInvoicesRepository invoicesRepository;
    private StockInvoiceMapper stockInvoiceMapper;

    @Autowired
    public GetStockInvoiceByIdUsecase(StockInvoicesRepository invoicesRepository, StockInvoiceMapper stockInvoiceMapper) {
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
