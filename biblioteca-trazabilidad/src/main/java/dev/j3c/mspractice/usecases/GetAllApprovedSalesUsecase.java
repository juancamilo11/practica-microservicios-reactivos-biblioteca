package dev.j3c.mspractice.usecases;

import dev.j3c.mspractice.usecases.interfaces.GetAllApprovedSales;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class GetAllApprovedSalesUsecase implements GetAllApprovedSales {


    @Override
    public Flux<> get() {
        return null;
    }
}
