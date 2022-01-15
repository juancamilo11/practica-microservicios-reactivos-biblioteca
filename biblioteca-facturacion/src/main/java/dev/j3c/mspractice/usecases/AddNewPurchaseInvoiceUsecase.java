package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import dev.j3c.mspractice.usecases.interfaces.AddNewPurchaseInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewPurchaseInvoiceUsecase implements AddNewPurchaseInvoice {

    private final PurchaseStockInvoiceRepository purchaseInvoiceRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;

    @Autowired
    public AddNewPurchaseInvoiceUsecase(PurchaseStockInvoiceRepository purchaseInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
    }

    @Override
    public Mono<PurchaseStockInvoiceDto> apply(PurchaseStockInvoiceDto purchaseStockInvoiceDto) {
        return purchaseInvoiceRepository.save(purchaseInvoiceMapper.mapFromDtoToEntity().apply(purchaseStockInvoiceDto))
                    .map(purchaseStockInvoice -> purchaseInvoiceMapper.mapFromEntityToDto().apply(purchaseStockInvoice));
    }
}
