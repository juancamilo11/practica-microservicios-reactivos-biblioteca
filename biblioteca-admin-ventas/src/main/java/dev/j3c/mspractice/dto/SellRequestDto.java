package dev.j3c.mspractice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.j3c.mspractice.collection.helpers.LibraryItem;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document
public class SellRequestDto {

    @NotBlank
    private String id;
    @NotNull
    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonProperty("date")
    private LocalDate date;
    @NotEmpty
    private List<LibraryItemDto> itemsList;
    @NotBlank
    private String customerId;
    @NotBlank
    private String customerName;

}
