package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.LocalDate;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StockInvoiceRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllPurchaseStockInvoicesRoute(GetAllPurchasePurchaseStockInvoicesUsecase getAllPurchaseStockInvoicesUsecase) {
        return route(GET("/get-all-purchase-stock-invoices")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllPurchaseStockInvoicesUsecase.get(),PurchaseStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllSellStockInvoicesRoute(GetAllSellStockInvoicesUsecase getAllSellStockInvoicesUsecase) {
        return route(GET("/get-all-sell-stock-invoices")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllSellStockInvoicesUsecase.get(),SellStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getPurchaseStockInvoiceByIdRoute(GetPurchaseStockInvoiceByIdUsecase getPurchaseStockInvoiceByIdUsecase) {
        return route(GET("/get-purchase-invoice/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getPurchaseStockInvoiceByIdUsecase.apply(request.pathVariable("id")), PurchaseStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getSellStockInvoiceByIdRoute(GetSellStockInvoiceByIdUsecase getSellStockInvoiceByIdUsecase) {
        return route(GET("/get-sell-invoice/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getSellStockInvoiceByIdUsecase.apply(request.pathVariable("id")), SellStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllStockInvoicesBetweenDatesRoute(GetStockInvoicesBetweenDatesUsecase getStockInvoicesBetweenDatesUsecase) {
        return route(GET("/get-stock-invoices-dates/{since}/{until}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(getStockInvoicesBetweenDatesUsecase
                                        .apply(LocalDate.parse(request.pathVariable("since")),
                                               LocalDate.parse(request.pathVariable("until"))),
                                        StockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllStockInvoicesBetweenPricesRoute(GetStockInvoicesBetweenPricesUsecase getStockInvoicesBetweenPricesUsecase) {
        return route(GET("/get-stock-invoices-prices/{minPrice}/{maxPrice}")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(getStockInvoicesBetweenPricesUsecase
                                                .apply(Double.parseDouble(request.pathVariable("minPrice")),
                                                        Double.parseDouble(request.pathVariable("maxPrice"))),
                                        StockInvoiceDto.class)));
    }

//    @Bean
//    public RouterFunction<ServerResponse> addPurchaseStockInvoiceRoute(AddNewPurchaseInvoiceUsecase addNewPurchaseInvoiceUsecase) {
//        Function<PurchaseStockInvoiceDto, Mono<ServerResponse>> executor = (PurchaseStockInvoiceDto purchaseStockInvoiceDto) ->  addNewPurchaseInvoiceUsecase
//                .apply(purchaseStockInvoiceDto)
//                .flatMap(purchaseInvoiceDto -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .bodyValue(purchaseInvoiceDto));
//        return route(POST("/add-purchase-invoice")
//                .and(accept(MediaType.APPLICATION_JSON)), request -> request
//                .bodyToMono(PurchaseStockInvoiceDto.class)
//                .flatMap(executor));
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> addSellStockInvoiceRoute(AddNewSellInvoiceUsecase addNewSellInvoiceUsecase) {
//        Function<SellStockInvoiceDto, Mono<ServerResponse>> executor = (SellStockInvoiceDto sellStockInvoiceDto) ->  addNewSellInvoiceUsecase
//                .apply(sellStockInvoiceDto)
//                .flatMap(sellInvoiceDto -> ServerResponse
//                        .ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .bodyValue(sellInvoiceDto));
//        return route(POST("/add-sell-invoice")
//                .and(accept(MediaType.APPLICATION_JSON)), request -> request
//                .bodyToMono(SellStockInvoiceDto.class)
//                .flatMap(executor));
//    }
}
