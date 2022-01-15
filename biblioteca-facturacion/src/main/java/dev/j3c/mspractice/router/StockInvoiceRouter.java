package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.PurchaseStockInvoiceDto;
import dev.j3c.mspractice.dto.SellStockInvoiceDto;
import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.usecases.GetAllPurchasePurchaseStockInvoicesUsecase;
import dev.j3c.mspractice.usecases.GetAllSellStockInvoicesUsecase;
import dev.j3c.mspractice.usecases.GetPurchaseStockInvoiceByIdUsecase;
import dev.j3c.mspractice.usecases.GetSellStockInvoiceByIdUsecase;
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

}
