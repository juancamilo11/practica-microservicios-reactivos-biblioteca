package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceMapper;
import dev.j3c.mspractice.repository.ProvidedResourcesRepository;
import dev.j3c.mspractice.usecases.interfaces.GetProvidedResourceById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetProvidedResourceByIdUsecase implements GetProvidedResourceById {

    private final ProvidedResourcesRepository providedResourcesRepository;
    private final ResourceMapper resourceMapper;

    @Autowired
    public GetProvidedResourceByIdUsecase(ProvidedResourcesRepository providedResourcesRepository, ResourceMapper resourceMapper) {
        this.providedResourcesRepository = providedResourcesRepository;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Mono<ResourceLoaningDto> apply(String id) {
        return this.providedResourcesRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(resourceLoaning -> this.resourceMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoaning));
    }
}
