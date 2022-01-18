package dev.j3c.mspractice.mapper;

import dev.j3c.mspractice.collection.LibraryItemForLoan;
import dev.j3c.mspractice.dto.LibraryItemForLoanDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LibraryItemsForLoanMapper {
    public Function<LibraryItemForLoan, LibraryItemForLoanDto> mapFromEntityToDto() {
        return (LibraryItemForLoan libraryItemForLoan) -> LibraryItemForLoanDto.builder()
                .id(libraryItemForLoan.getId())
                .name(libraryItemForLoan.getName())
                .author(libraryItemForLoan.getAuthor())
                .format(libraryItemForLoan.getFormat())
                .purchasePrice(libraryItemForLoan.getPurchasePrice())
                .availableUnits(libraryItemForLoan.getAvailableUnits())
                .loanedUnits(libraryItemForLoan.getLoanedUnits())
                .build();
    }

    public Function<LibraryItemForLoanDto, LibraryItemForLoan> mapFromDtoToEntity() {
        return (LibraryItemForLoanDto libraryItemForLoanDto) -> LibraryItemForLoan.builder()
                .id(libraryItemForLoanDto.getId())
                .name(libraryItemForLoanDto.getName())
                .author(libraryItemForLoanDto.getAuthor())
                .format(libraryItemForLoanDto.getFormat())
                .purchasePrice(libraryItemForLoanDto.getPurchasePrice())
                .availableUnits(libraryItemForLoanDto.getAvailableUnits())
                .loanedUnits(libraryItemForLoanDto.getLoanedUnits())
                .build();
    }
}
