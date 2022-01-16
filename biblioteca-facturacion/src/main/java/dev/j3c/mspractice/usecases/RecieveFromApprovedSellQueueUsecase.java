package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
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

    private final Gson gson = new Gson();
    private final SellStockInvoiceRepository sellInvoiceRepository;
    private final SellInvoiceMapper sellInvoiceMapper;
    private final Logger logger = LoggerFactory.getLogger(RecieveFromApprovedSellQueueUsecase.class);

    @Autowired
    public RecieveFromApprovedSellQueueUsecase(SellStockInvoiceRepository sellInvoiceRepository, SellInvoiceMapper sellInvoiceMapper) {
        this.sellInvoiceRepository = sellInvoiceRepository;
        this.sellInvoiceMapper = sellInvoiceMapper;
    }

    private void calculateTotalPrice(SellStockInvoiceDto sellStockInvoiceDto) {
        sellStockInvoiceDto
                .setTotalPrice(sellStockInvoiceDto
                        .getItemsList()
                        .stream()
                        .mapToDouble(LibraryItemDto::getPurchasePrice)
                        .reduce(0, Double::sum));
    }

    public void receiveMessage(SellStockInvoiceDto sellStockInvoiceDto) {
        this.calculateTotalPrice(sellStockInvoiceDto);
        sellStockInvoiceDto.setInvoiceType("Sale");
        logger.info("enviando factura de venta");
        this.sellInvoiceRepository
                .save(this.sellInvoiceMapper
                        .mapFromDtoToEntity()
                        .apply(sellStockInvoiceDto)).subscribe();
    }
}
