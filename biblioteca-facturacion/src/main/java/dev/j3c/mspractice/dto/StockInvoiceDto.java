package dev.j3c.mspractice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.j3c.mspractice.dto.helpers.LibraryItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInvoiceDto {

    private String id;

    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonProperty("date")
    private LocalDate date;


    private List<LibraryItemDto> itemsList;
    private double totalPrice;
}
