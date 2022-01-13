package dev.j3c.mspractice.router;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.UserDto;
import dev.j3c.mspractice.usecases.AddUserUsecase;
import jdk.jfr.ContentType;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Log
public class UserRouter {

    private static final Logger logger = LoggerFactory.getLogger(UserRouter.class);

    @Bean
    public RouterFunction<ServerResponse> addUserRouter(AddUserUsecase addUserUsecase) {
        logger.info("Se ha recibido la petición de agregar usuario");
        Function<UserDto, Mono<ServerResponse>> executor = (UserDto userDto) ->  addUserUsecase
                .apply(userDto)
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user));
        return route(POST("/create-user")
                .and(accept(MediaType.APPLICATION_JSON)), response -> response
                .bodyToMono(UserDto.class)
                .flatMap(executor));
    }


}
