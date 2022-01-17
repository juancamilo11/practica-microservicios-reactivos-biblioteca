package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.usecases.DeleteResourceLoanUsecase;
import dev.j3c.mspractice.usecases.GetAllResourceLoansUsecase;
import dev.j3c.mspractice.usecases.ReceiveResourceLoanUsecase;
import dev.j3c.mspractice.usecases.interfaces.GetResourceLoanByCustomerId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ResourceLoaningRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllResourceLoansRoute(GetAllResourceLoansUsecase getAllResourceLoansUsecase) {
        return route(GET("/get-all-active-resource-loans")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllResourceLoansUsecase.get(), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllResourceLoansByCustomerIdRoute(GetResourceLoanByCustomerId getResourceLoanByCustomerId) {
        return route(GET("/get-customer-active-resource-loans/{customerId}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getResourceLoanByCustomerId.apply(request.pathVariable("customerId")), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> registerResourceLoaningRoute(ReceiveResourceLoanUsecase recieveResourceLoanUsecase) {
        Function<ResourceLoaningDto, Mono<ServerResponse>> executor = (ResourceLoaningDto resourceLoaningDto) ->  recieveResourceLoanUsecase
                .apply(resourceLoaningDto)
                .flatMap(resultOfResourceLoanDto -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resultOfResourceLoanDto));
        return route(POST("/register-resource-loan")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(ResourceLoaningDto.class)
                .flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLoanForResourceDevolutionRoute(DeleteResourceLoanUsecase deleteResourceLoanUsecase) {
        return route(DELETE("/delete-user/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteResourceLoanUsecase.accept(request.pathVariable("id")),Void.class)));
    }


}
