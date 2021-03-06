package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceMapper;
import dev.j3c.mspractice.repository.ProvidedResourcesRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllProvidedResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllProvidedResourcesUsecase implements GetAllProvidedResources {

    private final ProvidedResourcesRepository providedResourcesRepository;
    private final ResourceMapper resourceMapper;
    @Autowired
    public GetAllProvidedResourcesUsecase(ProvidedResourcesRepository providedResourcesRepository, ResourceMapper resourceMapper) {
        this.providedResourcesRepository = providedResourcesRepository;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Flux<ResourceLoaningDto> get() {
        return this.providedResourcesRepository
                .findAllByType("Resource Provided")
                .switchIfEmpty(Flux.empty())
                .map(resourceLoaning -> this.resourceMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoaning));
    }
}
