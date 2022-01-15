package dev.j3c.mspractice.usecases;


import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.AddNewSellInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewSellInvoiceUsecase implements AddNewSellInvoice {
    private final SellStockInvoiceRepository sellInvoiceRepository;
    private final SellInvoiceMapper sellInvoiceMapper;

    @Autowired
    public AddNewSellInvoiceUsecase(SellStockInvoiceRepository sellInvoiceRepository, SellInvoiceMapper sellInvoiceMapper) {
        this.sellInvoiceRepository = sellInvoiceRepository;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    @Override
    public Mono<SellStockInvoiceDto> apply(SellStockInvoiceDto sellStockInvoiceDto) {
        return sellInvoiceRepository.save(sellInvoiceMapper.mapFromDtoToEntity().apply(sellStockInvoiceDto))
                .map(purchaseStockInvoice -> sellInvoiceMapper.mapFromEntityToDto().apply(purchaseStockInvoice));
    }
}
