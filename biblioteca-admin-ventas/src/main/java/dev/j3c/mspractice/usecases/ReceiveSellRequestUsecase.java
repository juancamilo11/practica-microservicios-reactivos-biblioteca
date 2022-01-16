package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.config.RabbitMQPublisherConfig;
import dev.j3c.mspractice.dto.SellRequestDto;
import dev.j3c.mspractice.mapper.SellRequestMapper;
import dev.j3c.mspractice.repository.SellRequestRepository;
import dev.j3c.mspractice.usecases.interfaces.ReceiveSellRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ReceiveSellRequestUsecase implements ReceiveSellRequest {

    private final SellRequestRepository sellRequestRepository;
    private final SellRequestMapper sellRequestMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ReceiveSellRequestUsecase(SellRequestRepository sellRequestRepository, SellRequestMapper sellRequestMapper, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.sellRequestRepository = sellRequestRepository;
        this.sellRequestMapper = sellRequestMapper;
    }

    @Override
    public Mono<SellRequestDto> apply(SellRequestDto sellRequestDto) {
        Gson gson = new Gson();
        this.rabbitTemplate.convertAndSend(RabbitMQPublisherConfig.EXCHANGE,RabbitMQPublisherConfig.ROUTING_KEY,gson.toJson(sellRequestDto));
        return sellRequestRepository.save(sellRequestMapper.mapFromDtoToEntity().apply(sellRequestDto))
                    .map(sellRequest -> sellRequestMapper.mapFromEntityToDto().apply(sellRequest));
    }
}
