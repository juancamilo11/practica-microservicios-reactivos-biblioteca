package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceMapper;
import dev.j3c.mspractice.repository.ProvidedResourcesRepository;
import dev.j3c.mspractice.usecases.interfaces.ReceiveFromProvidedResourcesQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ReceiveFromProvidedResourcesQueueUsecase implements ReceiveFromProvidedResourcesQueue {
    private final ProvidedResourcesRepository providedResourcesRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public ReceiveFromProvidedResourcesQueueUsecase(ProvidedResourcesRepository providedResourcesRepository, ResourceMapper resourceMapper) {
        this.providedResourcesRepository = providedResourcesRepository;
        this.resourceMapper = resourceMapper;
    }

    public void receiveMessage(ResourceLoaningDto resourceLoaningDto) {
        this.providedResourcesRepository
                .save(this.resourceMapper
                        .mapFromDtoToEntity()
                        .apply(resourceLoaningDto)).subscribe();
    }
}

