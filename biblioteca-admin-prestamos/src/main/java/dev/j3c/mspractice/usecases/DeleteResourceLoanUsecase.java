package dev.j3c.mspractice.usecases;

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

    @Autowired
    public DeleteResourceLoanUsecase(ResourceLoaningRepository resourceLoaningRepository) {
        this.resourceLoaningRepository = resourceLoaningRepository;
    }

    @Override
    public Mono<Void> accept(String id) {
        return this.resourceLoaningRepository
                .deleteById(id)
                .then();
    }
}
