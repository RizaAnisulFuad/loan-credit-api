package com.enigma.load_credit_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequest  {
    private String id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthDate;
}
