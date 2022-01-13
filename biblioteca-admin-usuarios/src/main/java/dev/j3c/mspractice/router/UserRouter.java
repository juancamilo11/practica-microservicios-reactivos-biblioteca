package dev.j3c.mspractice.router;

import com.google.gson.Gson;
import com.mongodb.internal.connection.Server;
import dev.j3c.mspractice.dto.UserDto;
import dev.j3c.mspractice.usecases.AddUserUsecase;
import dev.j3c.mspractice.usecases.GetAllUsersUsecase;
import dev.j3c.mspractice.usecases.GetUserByIdUsecase;
import dev.j3c.mspractice.usecases.UpdateUserByIdUsecase;
import jdk.jfr.ContentType;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter {

    private static final Logger logger = LoggerFactory.getLogger(UserRouter.class);

    @Bean
    public RouterFunction<ServerResponse> addUserRoute(AddUserUsecase addUserUsecase) {
        logger.info("Se ha recibido la petición de agregar usuario");
        Function<UserDto, Mono<ServerResponse>> executor = (UserDto userDto) ->  addUserUsecase
                .apply(userDto)
                .flatMap(user -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user));
        return route(POST("/create-user")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(UserDto.class)
                .flatMap(executor));
    }

    @Bean
    public RouterFunction<ServerResponse> getUserByIdRoute(GetUserByIdUsecase getUserByIdUsecase) {
        return route(GET("/get-user/{id}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getUserByIdUsecase.apply(request.pathVariable("id")),UserDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllUsers(GetAllUsersUsecase getAllUsersUsecase) {
        return route(GET("/get-users")
                .and(accept(MediaType.APPLICATION_JSON)), request ->
                ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllUsersUsecase.get(),UserDto.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> updateUserById(UpdateUserByIdUsecase updateUserById) {
        Function<UserDto, Mono<ServerResponse>> executor = (UserDto userDto) ->
                updateUserById.apply(userDto)
                        .flatMap(userUpdatedDto -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(userUpdatedDto));

        return route(PUT("/update-user")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request
                .bodyToMono(UserDto.class)
                .flatMap(executor));
    }
}
