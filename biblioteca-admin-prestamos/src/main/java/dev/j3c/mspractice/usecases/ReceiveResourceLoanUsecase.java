package dev.j3c.mspractice.usecases;

import com.google.gson.Gson;
import dev.j3c.mspractice.dto.ResourceLoaningDto;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import dev.j3c.mspractice.mapper.ResourceLoaningMapper;
import dev.j3c.mspractice.repository.ResourceLoaningRepository;
import dev.j3c.mspractice.usecases.interfaces.ReceiveResourceLoan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class ReceiveResourceLoanUsecase implements ReceiveResourceLoan {

    private final ResourceLoaningRepository resourceLoaningRepository;
    private final ResourceLoaningMapper resourceLoaningMapper;

    private final PostLoanResourceMessageToRabbitMQUsecase postResourceLoanMessageUsecase;

    private static final Logger logger = LoggerFactory.getLogger(ReceiveResourceLoanUsecase.class);
    @Value("${user.existence.verification.url}")
    private String userVerificationUrl;

    @Value("${stock.availability.verification.url}")
    private String stockAvailabilityVerificationUrl;

    @Autowired
    public ReceiveResourceLoanUsecase(ResourceLoaningRepository resourceLoaningRepository,
                                      ResourceLoaningMapper resourceLoaningMapper,
                                      PostLoanResourceMessageToRabbitMQUsecase postResourceLoanMessageUsecase) {
        this.resourceLoaningRepository = resourceLoaningRepository;
        this.resourceLoaningMapper = resourceLoaningMapper;
        this.postResourceLoanMessageUsecase = postResourceLoanMessageUsecase;
    }

    private boolean verifyCustomer(@NotBlank String customerId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(userVerificationUrl + customerId)).build();
        boolean userExistence = false;
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info(httpResponse.body());
            if(httpResponse.statusCode() == 200) {
                userExistence = true;
                logger.info("Se ha encontrado al usuario existosamente");
            }
        } catch(IOException | InterruptedException e) {
            logger.error("Error al intentar conectar con el microservicio de administración de clientes.");
        }
        return userExistence;
    }

    private boolean verifyResourcesAvailability(List<LibraryItemDto> itemsList) {
        Map<String, List<LibraryItemDto>> mapResourcesGroupedByResourceId = itemsList.stream().collect(Collectors.groupingBy(LibraryItemDto::getId));
        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(mapResourcesGroupedByResourceId)))
                .uri(URI.create(stockAvailabilityVerificationUrl))
                .build();
        boolean stockVerificationSuccessful = false;
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info(httpResponse.body());
            if(httpResponse.statusCode() == 200) {
                stockVerificationSuccessful = true;
                logger.info("El inventario solicitado para préstamo si existe en su totalidad, se procederá a realizar el préstamo");
            }
        } catch(IOException | InterruptedException e) {
            logger.error("Error al intentar conectar con el microservicio de administración de clientes.");
        }
        return stockVerificationSuccessful;
    }

    @Override
    public Mono<ResourceLoaningDto> apply(ResourceLoaningDto resourceLoaningDto) {

        if(!verifyCustomer(resourceLoaningDto.getCustomerId()))
            return Mono.error(new IllegalArgumentException("Error, el usuario con id:" + resourceLoaningDto.getCustomerId() + " no existe en el sistema."));
        if(!verifyResourcesAvailability(resourceLoaningDto.getItemsList()))
            return Mono.error(new IllegalArgumentException("Error, no hay disponibilidad de uno o varios recursos."));

        //Send message to RabbitMQ
        this.postResourceLoanMessageUsecase.accept(resourceLoaningDto);

        return this.resourceLoaningRepository
                .save(this.resourceLoaningMapper
                        .mapFromDtoToEntity()
                        .apply(resourceLoaningDto))
                .map(resourceLoan -> this.resourceLoaningMapper
                        .mapFromEntityToDto()
                        .apply(resourceLoan));
    }

}
