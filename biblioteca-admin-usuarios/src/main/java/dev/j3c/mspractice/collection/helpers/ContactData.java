package dev.j3c.mspractice.collection.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@builder(toBuilder = true)
public class ContactData {
    private String address;
    private String phoneNumber;
    private String email;
}
