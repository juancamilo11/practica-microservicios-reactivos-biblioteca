package dev.j3c.mspractice.config;

import dev.j3c.mspractice.usecases.RecieveFromApprovedSellQueueUsecase;
import dev.j3c.mspractice.usecases.RecieveFromNewStockQueueUsecase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    public static final String NEW_STOCK_QUEUE = "new.stock.queue";
    public static final String APPROVED_SELL_QUEUE = "approved.sell.queue";

    @Autowired
    RecieveFromApprovedSellQueueUsecase approvedSellMessageReciever;
    @Autowired
    RecieveFromNewStockQueueUsecase newStockMessageReciever;

    @RabbitListener(queues = {APPROVED_SELL_QUEUE})
    public void listenerOfApprovedSellQueue(String messageReceived){
        approvedSellMessageReciever.receiveMessage(messageReceived);
    }

    @RabbitListener(queues = {NEW_STOCK_QUEUE})
    public void listenerOfNewStockQueue(String messageReceived){
        newStockMessageReciever.receiveMessage(messageReceived);
    }

}
