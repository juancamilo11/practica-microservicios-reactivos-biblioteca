package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import dev.j3c.mspractice.mapper.PurchaseInvoiceMapper;
import dev.j3c.mspractice.repository.PurchaseStockInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RecieveFromNewStockQueueUsecase {

    private final Gson gson = new Gson();
    private final PurchaseStockInvoiceRepository purchaseInvoiceRepository;
    private final PurchaseInvoiceMapper purchaseInvoiceMapper;
    private final Logger logger = LoggerFactory.getLogger(RecieveFromNewStockQueueUsecase.class);

    @Autowired
    public RecieveFromNewStockQueueUsecase(PurchaseStockInvoiceRepository purchaseInvoiceRepository, PurchaseInvoiceMapper purchaseInvoiceMapper) {
        this.purchaseInvoiceRepository = purchaseInvoiceRepository;
        this.purchaseInvoiceMapper = purchaseInvoiceMapper;
    }

    private void calculateTotalPrice(PurchaseStockInvoiceDto purchaseStockInvoiceDto) {
        purchaseStockInvoiceDto
                .setTotalPrice(purchaseStockInvoiceDto
                        .getItemsList()
                        .stream()
                        .mapToDouble(LibraryItemDto::getPurchasePrice)
                        .reduce(0, Double::sum));
    }

    public void receiveMessage(PurchaseStockInvoiceDto purchaseStockInvoiceDto) {
        this.calculateTotalPrice(purchaseStockInvoiceDto);
        purchaseStockInvoiceDto.setInvoiceType("Purchase");
        logger.info("enviando factura de venta");
        this.purchaseInvoiceRepository
                .save(this.purchaseInvoiceMapper
                        .mapFromDtoToEntity()
                        .apply(purchaseStockInvoiceDto)).subscribe();
    }

}
