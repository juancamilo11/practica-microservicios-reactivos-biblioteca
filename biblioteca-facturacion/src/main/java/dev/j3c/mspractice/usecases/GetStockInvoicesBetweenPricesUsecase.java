package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.GetStockInvoicesBetweenPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Min;

@Service
@Validated
public class GetStockInvoicesBetweenPricesUsecase implements GetStockInvoicesBetweenPrices {
    private final PurchaseStockInvoiceRepository purchaseInvoiceRepository;
    private final SellStockInvoiceRepository sellStockInvoiceRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;
    private final SellInvoiceMapper sellInvoiceMapper;

    @Autowired
    public GetStockInvoicesBetweenPricesUsecase(PurchaseStockInvoiceRepository purchaseInvoiceRepository, SellStockInvoiceRepository sellStockInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper, SellInvoiceMapper sellInvoiceMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.sellStockInvoiceRepository = sellStockInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    @Override
    public Flux<StockInvoiceDto> apply(@Min(value = 0) double minPrice, @Min(value = 0)  double maxPrice) {
        if(minPrice > maxPrice) return Flux.empty();

        return Flux.concat(purchaseInvoiceRepository
                        .findAllByTotalPriceBetween(minPrice, maxPrice)
                        .map(invoice -> purchaseInvoiceMapper
                                .mapFromEntityToDto()
                                .apply(invoice)),
                sellStockInvoiceRepository
                        .findAllByTotalPriceBetween(minPrice, maxPrice)
                        .map(invoice -> sellInvoiceMapper
                                .mapFromEntityToDto()
                                .apply(invoice)));
    }
}
