package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.DeleteResourceLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteResourceLoanUsecase implements DeleteResourceLoan {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;
    private final PostReturnResourceMessageToRabbitMQUsecase postReturnResourceMessageUsecase;

    @Autowired
    public DeleteResourceLoanUsecase(ResourceLoaningRepository resourceLoaningRepository,
                                     ResourceLoaningMapper resourceLoaningMapper,
                                     PostReturnResourceMessageToRabbitMQUsecase postReturnResourceMessageUsecase) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this. resourceLoaningMapper = resourceLoaningMapper;
        this.postReturnResourceMessageUsecase = postReturnResourceMessageUsecase;
    }

    @Override
    public Mono<Void> accept(String id) {

        //Send message to RabbitMQ
        Mono<ResourceLoaningDto> resourceDevolution = this.resourceLoaningRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El préstamo con Id" + id + " no ha sido encontrado.")))
                .map(resourceLoaning -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoaning));

        this.postReturnResourceMessageUsecase.accept(resourceDevolution);
        return this.resourceLoaningRepository
                .deleteById(id)
                .then();
    }
}
