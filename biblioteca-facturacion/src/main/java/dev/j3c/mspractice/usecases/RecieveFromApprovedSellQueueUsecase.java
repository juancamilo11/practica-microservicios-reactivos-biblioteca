package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.mapper.SellInvoiceMapper;
import dev.j3c.mspractice.repository.SellStockInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RecieveFromApprovedSellQueueUsecase {

    private final Gson gson;
    private final SellStockInvoiceRepository sellInvoiceRepository;
    private final SellInvoiceMapper sellInvoiceMapper;
    private final Logger logger = LoggerFactory.getLogger(RecieveFromApprovedSellQueueUsecase.class);

    @Autowired
    public RecieveFromApprovedSellQueueUsecase(SellStockInvoiceRepository sellInvoiceRepository, SellInvoiceMapper sellInvoiceMapper) {
        this.gson = new Gson();
        this.sellInvoiceRepository = sellInvoiceRepository;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    public void receiveMessage(String messageReceived) {
        this.sellInvoiceRepository
                .save(this.sellInvoiceMapper
                        .mapFromDtoToEntity()
                        .apply(gson.fromJson(messageReceived, SellStockInvoiceDto.class)))
                .doOnNext(invoiceRecieved -> logger
                        .info("Se ha ingresado la factura de venta: " + gson.toJson(invoiceRecieved)));
    }
}
