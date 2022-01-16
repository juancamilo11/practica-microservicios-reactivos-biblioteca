package dev.j3c.mspractice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import dev.j3c.mspractice.usecases.RecieveFromApprovedSellQueueUsecase;
import dev.j3c.mspractice.usecases.RecieveFromNewStockQueueUsecase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RabbitMQConsumerConfig {

    public static final String NEW_STOCK_QUEUE = "new.stock.queue";
    public static final String APPROVED_SELL_QUEUE = "approved.sell.queue";

    private final RecieveFromApprovedSellQueueUsecase approvedSellMessageReciever;
    private final RecieveFromNewStockQueueUsecase newStockMessageReciever;

    @Autowired
    public RabbitMQConsumerConfig(RecieveFromApprovedSellQueueUsecase approvedSellMessageReciever, RecieveFromNewStockQueueUsecase newStockMessageReciever) {
        this.approvedSellMessageReciever = approvedSellMessageReciever;
        this.newStockMessageReciever = newStockMessageReciever;
    }

    @RabbitListener(queues = {APPROVED_SELL_QUEUE})
    public void listenerOfApprovedSellQueue(String messageReceived){
        Gson gson = new Gson();
        try {
            gson.fromJson(messageReceived, PurchaseStockInvoiceDto.class);
            approvedSellMessageReciever.receiveMessage(messageReceived);
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private List<String> getLibraryItemsList(String itemsList) {

        return Arrays.stream(itemsList
                .trim()
                .substring(1, itemsList.length() - 1)
                .split("\\}, \\{"))
                .map(itemList -> itemList.trim().charAt(0)!= '{' ? '{' + itemList : itemList)
                .map(itemList -> itemList.trim().charAt(itemList.length() -1 ) != '}' ? itemList + '}': itemList)
                .map(itemList -> itemList.replaceAll("=",":"))
                .collect(Collectors.toList());
    }

    private List<String> getListItems(String listItems) {
        Gson gson = new Gson();
        return this.getLibraryItemsList(listItems)
                .stream()
                //.map(item -> gson.fromJson(item, LibraryItemDto.class))
                .collect(Collectors.toList());
    }

    @RabbitListener(queues = {NEW_STOCK_QUEUE})
    public void listenerOfNewStockQueue(String messageReceived) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(messageReceived, Map.class);
        PurchaseStockInvoiceDto newPurchaseInvoice = new PurchaseStockInvoiceDto(
                map.get("id").toString(),
                LocalDate.parse(map.get("date").toString()),
                List.of(new LibraryItemDto(), new LibraryItemDto()),
                10,
                map.get("nit").toString(),
                map.get("providerName").toString()
        );

        this.getListItems(map.get("itemsList").toString()).forEach(System.out::println);
        //System.out.println(map.get("itemsList").toString());
    }


}
