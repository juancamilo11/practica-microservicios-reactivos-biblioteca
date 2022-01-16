package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.SellRequest;
import dev.j3c.mspractice.dto.SellRequestDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SellRequestMapper {

    public Function<SellRequest, SellRequestDto> mapFromEntityToDto() {
        return (SellRequest sellRequest) -> SellRequestDto.builder().build();
    }

    public Function<SellRequestDto, SellRequest> mapFromDtoToEntity() {
        return (SellRequestDto sellRequestDto) -> SellRequest.builder().build();
    }
}
