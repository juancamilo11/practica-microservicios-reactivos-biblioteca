package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.GetApprovedSaleById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GetApprovedSaleByIdUsecase implements GetApprovedSaleById {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;

    @Autowired
    public GetApprovedSaleByIdUsecase(ResourceLoaningRepository resourceLoaningRepository, ResourceLoaningMapper resourceLoaningMapper) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this.resourceLoaningMapper = resourceLoaningMapper;
    }

    @Override
    public Mono<ResourceLoaningDto> apply(String id) {
        return this.resourceLoaningRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(resourceLoaning -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoaning));
    }
}
