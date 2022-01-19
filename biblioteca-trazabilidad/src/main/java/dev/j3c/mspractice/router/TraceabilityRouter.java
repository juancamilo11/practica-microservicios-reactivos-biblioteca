package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.usecases.GetAllProvidedResourcesUsecase;
import dev.j3c.mspractice.usecases.GetAllReturnedResourcesUsecase;
import dev.j3c.mspractice.usecases.GetProvidedResourceByIdUsecase;
import dev.j3c.mspractice.usecases.GetReturnedResourceByIdUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TraceabilityRouter.class);

    @Bean
    public RouterFunction<ServerResponse> getAllProvidedResourcesRoute(GetAllProvidedResourcesUsecase getAllProvidedResourcesUsecase) {
        return route(GET("/get-all-provided-resources")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProvidedResourcesUsecase.get()
                                .doOnNext(result -> logger.info("[MS-TRACEABILITY] Get All Provided Resources " + result)), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getProvidedResourceByLoanIdRoute(GetProvidedResourceByIdUsecase getProvidedResourceByIdUsecase) {
        return route(GET("/get-provided-resource/{loanId}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getProvidedResourceByIdUsecase.apply(request.pathVariable("loanId"))
                                .doOnNext(result -> logger.info("[MS-TRACEABILITY] Get Provided Resource By Loan Id " + result)), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllReturnedResourcesRoute(GetAllReturnedResourcesUsecase getAllReturnedResourcesUsecase) {
        return route(GET("/get-all-returned-resources")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllReturnedResourcesUsecase.get()
                                .doOnNext(result -> logger.info("[MS-TRACEABILITY] Get All Returned Resources " + result)), ResourceLoaningDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getReturnedResourceByLoanIdRoute(GetReturnedResourceByIdUsecase getReturnedResourceByIdUsecase) {
        return route(GET("/get-returned-resource/{loanId}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getReturnedResourceByIdUsecase.apply(request.pathVariable("loanId"))
                                .doOnNext(result -> logger.info("[MS-TRACEABILITY] Get Returned Resource By Id " + result)), ResourceLoaningDto.class)));
    }

}
