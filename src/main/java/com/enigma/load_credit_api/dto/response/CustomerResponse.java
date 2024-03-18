package com.enigma.load_credit_api.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String birthDate;
    private Boolean status;
    private String userAccountId;
}
