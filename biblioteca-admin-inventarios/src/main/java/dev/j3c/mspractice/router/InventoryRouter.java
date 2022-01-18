package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import dev.j3c.mspractice.usecases.*;
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
public class InventoryRouter {
    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForLoansRoute(GetAllLibraryItemsForLoaningUsecase getAllLibraryItemsForLoaningUsecase) {
        return route(GET("/get-all-library-items-for-loaning")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLibraryItemsForLoaningUsecase.get(), LibraryItemForLoanDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForLoansByCustomerIdRoute(GetLibraryItemForLoaningByIdUsecase getLibraryItemForLoaningByIdUsecase) {
        return route(GET("/get-library-item-for-loaning/{id}")
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
        return route(POST("/register-library-item-for-loaning")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(LibraryItemForLoanDto.class)
                .flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLibraryItemsForLoaningByIdRoute(DeleteLibraryItemsForLoaningByidUsecase deleteLibraryItemsForLoaningByidUsecase) {
        return route(DELETE("/delete-library-item-for-loaning/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteLibraryItemsForLoaningByidUsecase.accept(request.pathVariable("id")),Void.class)));
    }



    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForSaleRoute(GetAllLibraryItemsForSaleUsecase getAllLibraryItemsForSaleUsecase) {
        return route(GET("/get-all-library-items-for-sale")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLibraryItemsForSaleUsecase.get(), LibraryItemForSaleDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForSaleByCustomerIdRoute(GetLibraryItemForSaleByIdUsecase getLibraryItemForSaleByIdUsecase) {
        return route(GET("/get-library-item-for-sale/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getLibraryItemForSaleByIdUsecase.apply(request.pathVariable("id")), LibraryItemForSaleDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> registerNewLibraryItemsForSaleRoute(AddNewLibraryItemForSaleUsecase addNewLibraryItemForSaleUsecase) {
        Function<LibraryItemForSaleDto, Mono<ServerResponse>> executor = (LibraryItemForSaleDto libraryItemForSaleDto) ->  addNewLibraryItemForSaleUsecase
                .apply(libraryItemForSaleDto)
                .flatMap(result -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(POST("/register-library-item-for-sale")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(LibraryItemForSaleDto.class)
                .flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLibraryItemsForSaleByIdRoute(DeleteLibraryItemsForLoaningByidUsecase deleteLibraryItemsForLoaningByidUsecase) {
        return route(DELETE("/delete-library-item-for-sale/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteLibraryItemsForLoaningByidUsecase.accept(request.pathVariable("id")),Void.class)));
    }
}
