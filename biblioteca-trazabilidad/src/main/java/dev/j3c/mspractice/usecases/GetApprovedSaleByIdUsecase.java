package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.usecases.interfaces.GetApprovedSaleById;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class GetApprovedSaleByIdUsecase implements GetApprovedSaleById {



    @Override
    public Flux<> apply(String id) {
        return null;
    }
}
