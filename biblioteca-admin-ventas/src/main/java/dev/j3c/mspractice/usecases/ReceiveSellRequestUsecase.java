package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.SellRequestDto;
import dev.j3c.mspractice.mapper.SellRequestMapper;
import dev.j3c.mspractice.repository.SellRequestRepository;
import dev.j3c.mspractice.usecases.interfaces.ReceiveSellRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ReceiveSellRequestUsecase implements ReceiveSellRequest {

    private final SellRequestRepository sellRequestRepository;
    private final SellRequestMapper sellRequestMapper;

    @Autowired
    public ReceiveSellRequestUsecase(SellRequestRepository sellRequestRepository, SellRequestMapper sellRequestMapper) {
        this.sellRequestRepository = sellRequestRepository;
        this.sellRequestMapper = sellRequestMapper;
    }

    @Override
    public Mono<SellRequestDto> apply(SellRequestDto sellRequestDto) {
        return sellRequestRepository.save(sellRequestMapper.mapFromDtoToEntity().apply(sellRequestDto))
                    .map(sellRequest -> sellRequestMapper.mapFromEntityToDto().apply(sellRequest));
    }
}
