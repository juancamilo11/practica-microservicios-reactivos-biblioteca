package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.usecases.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StockInvoiceRouter {

    private static final Logger logger = LoggerFactory.getLogger(StockInvoiceRouter.class);

    @Bean
    public RouterFunction<ServerResponse> getAllItemsPurchasedInvoicesRoute(GetAllPurchasePurchaseStockInvoicesUsecase getAllPurchaseStockInvoicesUsecase) {
        return route(GET("/get-all-purchase-stock-invoices")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllPurchaseStockInvoicesUsecase.get()
                                .doOnNext(result -> logger.info("[MS-INVOICING] Get All Invoices Type:'Purchase' " + result)), PurchaseStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllItemsSoldInvoicesRoute(GetAllSellStockInvoicesUsecase getAllSellStockInvoicesUsecase) {
        return route(GET("/get-all-sell-stock-invoices")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllSellStockInvoicesUsecase.get()
                                .doOnNext(result -> logger.info("[MS-INVOICING] Get All Invoices Type:'Sell' " + result)), SellStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getItemsPurchasedInvoiceByIdRoute(GetPurchaseStockInvoiceByIdUsecase getPurchaseStockInvoiceByIdUsecase) {
        return route(GET("/get-purchase-invoice/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getPurchaseStockInvoiceByIdUsecase.apply(request.pathVariable("id"))
                        .doOnNext(result -> logger.info("[MS-INVOICING] Get All Items purchased invoices " + result)), PurchaseStockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getSellStockInvoiceByIdRoute(GetSellStockInvoiceByIdUsecase getSellStockInvoiceByIdUsecase) {
        return route(GET("/get-sell-invoice/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getSellStockInvoiceByIdUsecase.apply(request.pathVariable("id"))
                        .doOnNext(result -> logger.info("[MS-INVOICING] Get Sale invoice By Id " + result)), SellStockInvoiceDto.class)));
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
                                               LocalDate.parse(request.pathVariable("until")))
                                                .doOnNext(result -> logger.info("[MS-INVOICING] Get All Invoices Between Two Dates " + result)),
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
                                                        Double.parseDouble(request.pathVariable("maxPrice")))
                                                .doOnNext(result -> logger.info("[MS-INVOICING] Get All Invoices Between Two prices " + result)),
                                        StockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> addPurchaseStockInvoiceRoute(AddNewPurchaseInvoiceUsecase addNewPurchaseInvoiceUsecase) {
        Function<PurchaseStockInvoiceDto, Mono<ServerResponse>> executor = (PurchaseStockInvoiceDto purchaseStockInvoiceDto) ->  addNewPurchaseInvoiceUsecase
                .apply(purchaseStockInvoiceDto)
                .flatMap(purchaseInvoiceDto -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(purchaseInvoiceDto));
        return route(POST("/add-purchase-invoice")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(PurchaseStockInvoiceDto.class)
                .flatMap(executor)
                .doOnNext(result -> logger.info("[MS-INVOICING] Add Items Purchased Invoice " + result)));
    }

    @Bean
    public RouterFunction<ServerResponse> addSellStockInvoiceRoute(AddNewSellInvoiceUsecase addNewSellInvoiceUsecase) {
        Function<SellStockInvoiceDto, Mono<ServerResponse>> executor = (SellStockInvoiceDto sellStockInvoiceDto) ->  addNewSellInvoiceUsecase
                .apply(sellStockInvoiceDto)
                .flatMap(sellInvoiceDto -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sellInvoiceDto));
        return route(POST("/add-sell-invoice")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(SellStockInvoiceDto.class)
                .flatMap(executor)
                .doOnNext(result -> logger.info("[MS-INVOICING] Add Items Sold Invoice " + result)));
    }
}
