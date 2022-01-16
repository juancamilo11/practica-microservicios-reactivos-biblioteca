package dev.j3c.mspractice.router;

import dev.j3c.mspractice.dto.SellRequestDto;
import dev.j3c.mspractice.usecases.ReceiveSellRequestUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SellRequestRouter {

    @Bean
    public RouterFunction<ServerResponse> addSellStockInvoiceRoute(ReceiveSellRequestUsecase receiveSellRequestUsecase) {
        Function<SellRequestDto, Mono<ServerResponse>> executor = (SellRequestDto sellRequestDto) ->  receiveSellRequestUsecase
                .apply(sellRequestDto)
                .flatMap(sellRequestResult -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sellRequestResult));
        return route(POST("/add-sell-request")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(SellRequestDto.class)
                .flatMap(executor));
    }
}
