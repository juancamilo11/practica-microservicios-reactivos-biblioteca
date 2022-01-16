package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
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

    public void receiveMessage(PurchaseStockInvoiceDto messageReceived) {
        logger.info("enviando factura de compra");
        this.purchaseInvoiceRepository
                .save(this.purchaseInvoiceMapper
                        .mapFromDtoToEntity()
                        .apply(messageReceived)).subscribe();
    }

}
