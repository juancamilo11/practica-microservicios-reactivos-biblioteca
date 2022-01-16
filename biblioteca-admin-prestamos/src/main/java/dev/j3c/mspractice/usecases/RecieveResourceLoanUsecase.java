package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.RecieveResourceLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class RecieveResourceLoanUsecase implements RecieveResourceLoan {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;

    @Autowired
    public RecieveResourceLoanUsecase(ResourceLoaningRepository resourceLoaningRepository, ResourceLoaningMapper resourceLoaningMapper) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this.resourceLoaningMapper = resourceLoaningMapper;
    }

    @Override
    public Mono<ResourceLoaningDto> apply(ResourceLoaningDto resourceLoaningDto) {
        return this.resourceLoaningRepository
                .save(this.resourceLoaningMapper
                        .mapFromDtoToEntity()
                        .apply(resourceLoaningDto))
                .map(resourceLoan -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoan));
    }
}
