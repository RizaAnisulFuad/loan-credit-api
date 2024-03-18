package com.enigma.load_credit_api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private Boolean status;
    private Integer page;
    private Integer size;
}
