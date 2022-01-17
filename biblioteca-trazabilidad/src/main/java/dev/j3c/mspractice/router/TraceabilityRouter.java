package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.usecases.GetAllApprovedSalesUsecase;
import dev.j3c.mspractice.usecases.GetApprovedSaleByIdUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TraceabilityRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllApprovedSalesRoute(GetAllApprovedSalesUsecase getAllApprovedSalesUsecase) {
        return route(GET("/get-all-approved-sales")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllApprovedSalesUsecase.get(), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllResourceLoansByCustomerIdRoute(GetApprovedSaleByIdUsecase getApprovedSaleByIdUsecase) {
        return route(GET("/get-approved-sale/{saleId}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getApprovedSaleByIdUsecase.apply(request.pathVariable("saleId")), ResourceLoaningDto.class)));
    }

}
