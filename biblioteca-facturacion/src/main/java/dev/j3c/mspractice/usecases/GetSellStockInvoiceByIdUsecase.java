package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetSellStockInvoiceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetSellStockInvoiceByIdUsecase implements GetSellStockInvoiceById {
    private final SellStockInvoiceRepository sellStockInvoiceRepository;
    private final SellInvoiceMapper sellInvoiceMapper;

    @Autowired
    public GetSellStockInvoiceByIdUsecase(SellStockInvoiceRepository sellStockInvoiceRepository, SellInvoiceMapper sellInvoiceMapper) {
        this.sellStockInvoiceRepository = sellStockInvoiceRepository;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    @Override
    public Mono<SellStockInvoiceDto> apply(String id) {
        return sellStockInvoiceRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error, la factura con id " + id + " no existe en el sistema.")))
                .map(invoice -> sellInvoiceMapper
                        .mapFromEntityToDto()
                        .apply(invoice));
    }
}
