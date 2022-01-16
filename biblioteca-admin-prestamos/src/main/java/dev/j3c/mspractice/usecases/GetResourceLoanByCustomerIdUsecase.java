package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.GetResourceLoanByCustomerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetResourceLoanByCustomerIdUsecase implements GetResourceLoanByCustomerId {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;

    @Autowired
    public GetResourceLoanByCustomerIdUsecase(ResourceLoaningRepository resourceLoaningRepository, ResourceLoaningMapper resourceLoaningMapper) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this.resourceLoaningMapper = resourceLoaningMapper;
    }

    @Override
    public Flux<ResourceLoaningDto> apply(String customerId) {
        return this.resourceLoaningRepository
                .findAllByCustomerId(customerId)
                .map(resourceLoaning -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoaning));
    }
}
