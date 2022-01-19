package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.CanonicoLibraryItemDto;
import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import dev.j3c.mspractice.dto.LibraryItemForSaleDto;
import dev.j3c.mspractice.usecases.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryRouter {

    @Autowired
    private VerifyItemsAvailabilityForLoaningUsecase verifyItemsAvailabilityForLoaningUsecase;

    private static Logger logger = LoggerFactory.getLogger(InventoryRouter.class);

    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForLoansRoute(GetAllLibraryItemsForLoaningUsecase getAllLibraryItemsForLoaningUsecase) {
        return route(GET("/get-all-library-items-for-loaning")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLibraryItemsForLoaningUsecase.get()
                                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Get All Library Items For Loaning " + result)), LibraryItemForLoanDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForLoansByCustomerIdRoute(GetLibraryItemForLoaningByIdUsecase getLibraryItemForLoaningByIdUsecase) {
        return route(GET("/get-library-item-for-loaning/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getLibraryItemForLoaningByIdUsecase.apply(request.pathVariable("id"))
                                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Get All Library Items For Loaning By Customer Id " + result)), LibraryItemForLoanDto.class)));
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
                .flatMap(executor)
                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Register New Library Item For Loaning " + result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLibraryItemsForLoaningByIdRoute(DeleteLibraryItemsForLoaningByidUsecase deleteLibraryItemsForLoaningByidUsecase) {
        return route(DELETE("/delete-library-item-for-loaning/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteLibraryItemsForLoaningByidUsecase.accept(request.pathVariable("id"))
                        .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Delete Library Items For Loaning By Id " + result)), Void.class)));
    }



    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForSaleRoute(GetAllLibraryItemsForSaleUsecase getAllLibraryItemsForSaleUsecase) {
        return route(GET("/get-all-library-items-for-sale")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllLibraryItemsForSaleUsecase.get()
                                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Get All Library Items For Sale " + result)), LibraryItemForSaleDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllLibraryItemsForSaleByCustomerIdRoute(GetLibraryItemForSaleByIdUsecase getLibraryItemForSaleByIdUsecase) {
        return route(GET("/get-library-item-for-sale/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getLibraryItemForSaleByIdUsecase.apply(request.pathVariable("id"))
                                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Get All Library Items For Sale By Customer Id " + result)), LibraryItemForSaleDto.class)));
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
                .flatMap(executor)
                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Register new Library Item For Sale " + result)));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteLibraryItemsForSaleByIdRoute(DeleteLibraryItemsForLoaningByidUsecase deleteLibraryItemsForLoaningByidUsecase) {
        return route(DELETE("/delete-library-item-for-sale/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleteLibraryItemsForLoaningByidUsecase.accept(request.pathVariable("id"))
                        .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Delete Library Items For Sale By Id " + result)), Void.class)));
    }

    @PostMapping("/verify-availability-for-loaning")
    public Mono<Boolean> verifyInventoryAvailabilityRoute(Map<String, List<CanonicoLibraryItemDto>> itemsGroupedById) {
        return this.verifyItemsAvailabilityForLoaningUsecase
                .apply(itemsGroupedById)
                .doOnNext(result -> logger.info("[MS-ADMIN_INVENTORY] Verify Items Availability For Loaning " + result));
    }

}
