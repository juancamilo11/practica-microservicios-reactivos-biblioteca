package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.config.RabbitMQPublisherConfig;
import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.usecases.interfaces.PostReturnResourceMessageToRabbitMQ;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class PostReturnResourceMessageToRabbitMQUsecase implements PostReturnResourceMessageToRabbitMQ {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PostReturnResourceMessageToRabbitMQUsecase(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Mono<Void> accept(Mono<ResourceLoaningDto> resourceLoaningDto) {
        Gson gson = new Gson();
        this.rabbitTemplate
                .convertAndSend(RabbitMQPublisherConfig.EXCHANGE,
                        RabbitMQPublisherConfig.RETURNED_RESOURCES_ROUTING_KEY,
                        gson.toJson(resourceLoaningDto));
        return Mono.just(resourceLoaningDto).then();
    }
}
