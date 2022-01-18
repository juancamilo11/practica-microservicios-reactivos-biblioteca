package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.usecases.AddNewLibraryItemForLoaningUsecase;
import dev.j3c.mspractice.usecases.GetAllLibraryItemsForLoaningUsecase;
import dev.j3c.mspractice.usecases.GetLibraryItemForLoaningByIdUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForLoansRoute(GetAllLibraryItemsForLoaningUsecase getAllLibraryItemsForLoaningUsecase) {
        return route(GET("/get-all-library-items-for-loans")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLibraryItemsForLoaningUsecase.get(), LibraryItemForLoanDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllResourceLoansByCustomerIdRoute(GetLibraryItemForLoaningByIdUsecase getLibraryItemForLoaningByIdUsecase) {
        return route(GET("/get-library-item-form-loan/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getLibraryItemForLoaningByIdUsecase.apply(request.pathVariable("id")), LibraryItemForLoanDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> registerNewLibraryItemsForLoaningRoute(AddNewLibraryItemForLoaningUsecase addNewLibraryItemForLoaningUsecase) {
        Function<LibraryItemForLoanDto, Mono<ServerResponse>> executor = (LibraryItemForLoanDto libraryItemForLoanDto) ->  addNewLibraryItemForLoaningUsecase
                .apply(libraryItemForLoanDto)
                .flatMap(result -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(POST("/register-library-item-for-loan")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(LibraryItemForLoanDto.class)
                .flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLibraryItemsByIdRoute(DeleteLibraryItemsByidUsecase deleteLibraryItemsByidUsecase) {
        return route(DELETE("/delete-resource-loan/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteLibraryItemsByidUsecase.accept(request.pathVariable("id")),Void.class)));
    }
}
