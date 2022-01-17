package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceMapper;
import dev.j3c.mspractice.repository.ReturnedResourcesRepository;
import dev.j3c.mspractice.usecases.interfaces.ReceiveFromReturnedResourcesQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ReceiveFromReturnedResourcesQueueUsecase implements ReceiveFromReturnedResourcesQueue {

    private final ReturnedResourcesRepository returnedResourcesRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public ReceiveFromReturnedResourcesQueueUsecase(ReturnedResourcesRepository returnedResourcesRepository, ResourceMapper resourceMapper) {
        this.returnedResourcesRepository = returnedResourcesRepository;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public void receiveMessage(ResourceLoaningDto resourceLoaningDto) {
         this.returnedResourcesRepository
                .save(this.resourceMapper
                        .mapFromDtoToEntity()
                        .apply(resourceLoaningDto)).subscribe();
    }
}
