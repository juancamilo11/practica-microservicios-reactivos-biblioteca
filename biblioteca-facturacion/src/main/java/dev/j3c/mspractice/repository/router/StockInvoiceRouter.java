package dev.j3c.mspractice.repository.router;

import dev.j3c.mspractice.dto.StockInvoiceDto;
import dev.j3c.mspractice.usecases.GetAllStockInvoicesUsecase;
import dev.j3c.mspractice.usecases.GetStockInvoiceByIdUsecase;
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
    public RouterFunction<ServerResponse> getStockInvoiceByIdRoute(GetStockInvoiceByIdUsecase getStockInvoiceByIdUsecase) {
        return route(GET("/get-invoice/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getStockInvoiceByIdUsecase.apply(request.pathVariable("id")), StockInvoiceDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllStockInvoicesRoute(GetAllStockInvoicesUsecase getAllUsersUsecase) {
        return route(GET("/get-all-stock-invoices")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllUsersUsecase.get(),StockInvoiceDto.class)));
    }

}
