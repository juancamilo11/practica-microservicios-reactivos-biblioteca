package dev.j3c.mspractice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import dev.j3c.mspractice.usecases.ReceiveFromProvidedResourcesQueueUsecase;
import dev.j3c.mspractice.usecases.ReceiveFromReturnedResourcesQueueUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RabbitMQConsumerConfig {

    public static final String PROVIDED_RESOURCES_QUEUE = "provided.resources.queue";
    public static final String RETURNED_RESOURCES_QUEUE = "returned.resources.queue";

    private final ReceiveFromProvidedResourcesQueueUsecase receiveFromProvidedResourcesQueueUsecase;
    private final ReceiveFromReturnedResourcesQueueUsecase receiveFromReturnedResourcesQueueUsecase;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumerConfig.class);

    @Autowired
    public RabbitMQConsumerConfig(ReceiveFromProvidedResourcesQueueUsecase receiveFromProvidedResourcesQueueUsecase,
                                  ReceiveFromReturnedResourcesQueueUsecase receiveFromReturnedResourcesQueueUsecase) {
        this.receiveFromProvidedResourcesQueueUsecase = receiveFromProvidedResourcesQueueUsecase;
        this.receiveFromReturnedResourcesQueueUsecase = receiveFromReturnedResourcesQueueUsecase;
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

    private List<LibraryItemDto> getListItems(String listItems) {
        return this.getLibraryItemsList(listItems)
                .stream()
                .map(itemString -> {
                    List<String> properties = Arrays
                            .stream(itemString.substring(1, itemString.length() -1)
                                    .split(", "))
                            .collect(Collectors.toList());
                    Map<String, Object> propertiesMap = new HashMap<>();
                    properties.forEach(element -> {
                        String property = element.split(":")[0];
                        Object value = element.split(":")[1];
                        propertiesMap.put(property, value);
                    });
                    return new LibraryItemDto(propertiesMap.get("id").toString(),
                            propertiesMap.get("name").toString(),
                            propertiesMap.get("author").toString(), propertiesMap.get("format").toString(),
                            Double.parseDouble(propertiesMap.get("purchasePrice").toString()));
                })
                .collect(Collectors.toList());
    }

    @RabbitListener(queues = {PROVIDED_RESOURCES_QUEUE})
    public void listenerOfNewStockQueue(String messageReceived) throws JsonProcessingException {
        logger.info("[MS-TRACEABILITY] Listening to " + PROVIDED_RESOURCES_QUEUE + "Queue");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(messageReceived, Map.class);
        if(map.get("itemsList").toString().equals("[]")) return;
        ResourceLoaningDto resourceLoaningDto = ResourceLoaningDto.builder()
                .id(map.get("id").toString())
                .date(LocalDate.parse(map.get("date").toString()))
                .itemsList(this.getListItems(map.get("itemsList").toString()))
                .customerId(map.get("customerId").toString())
                .customerName(map.get("customerName").toString())
                .type("Resource Provided")
                .build();
        receiveFromProvidedResourcesQueueUsecase.receiveMessage(resourceLoaningDto);
    }

    @RabbitListener(queues = {RETURNED_RESOURCES_QUEUE})
    public void listenerOfApprovedSellQueue(String messageReceived) throws JsonProcessingException {
        logger.info("[MS-TRACEABILITY] Listening to " + RETURNED_RESOURCES_QUEUE + "Queue");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(messageReceived, Map.class);
        if(map.get("itemsList").toString().equals("[]")) return;
        ResourceLoaningDto resourceLoaningDto = ResourceLoaningDto.builder()
                .id(map.get("id").toString())
                .date(LocalDate.parse(map.get("date").toString()))
                .itemsList(this.getListItems(map.get("itemsList").toString()))
                .customerId(map.get("customerId").toString())
                .customerName(map.get("customerName").toString())
                .type("Resource Returned")
                .build();
        receiveFromReturnedResourcesQueueUsecase.receiveMessage(resourceLoaningDto);
    }

}
