package dev.j3c.mspractice.usecases.interfaces;

import dev.j3c.mspractice.dto.ResourceLoaningDto;

public interface ReceiveFromReturnedResourcesQueue {
    void receiveMessage(ResourceLoaningDto sellStockInvoiceDto);
}
