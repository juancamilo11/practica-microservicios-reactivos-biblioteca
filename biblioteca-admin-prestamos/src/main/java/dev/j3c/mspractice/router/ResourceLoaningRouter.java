package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.usecases.GetAllLoanResourcesUsecase;
import dev.j3c.mspractice.usecases.RecieveResourceLoanUsecase;
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
    public RouterFunction<ServerResponse> getAllResourceLoans(GetAllLoanResourcesUsecase getAllLoanResourcesUsecase) {
        return route(GET("/get-all-resource-active-loans")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLoanResourcesUsecase.get(), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> registerResourceLoaningRoute(RecieveResourceLoanUsecase recieveResourceLoanUsecase) {
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
    public RouterFunction<ServerResponse> deleteLoanforResourceDevolution() {

    }

}
