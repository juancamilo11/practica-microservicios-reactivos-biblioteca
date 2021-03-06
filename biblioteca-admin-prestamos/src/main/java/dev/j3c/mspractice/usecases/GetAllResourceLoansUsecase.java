package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.GetAllResourceLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllResourceLoansUsecase implements GetAllResourceLoans {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;

    @Autowired
    public GetAllResourceLoansUsecase(ResourceLoaningRepository resourceLoaningRepository, ResourceLoaningMapper resourceLoaningMapper) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this.resourceLoaningMapper = resourceLoaningMapper;
    }

    @Override
    public Flux<ResourceLoaningDto> get() {
        return this.resourceLoaningRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(resourceLoan -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoan));
    }
}
