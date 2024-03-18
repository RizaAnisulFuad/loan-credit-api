package com.enigma.load_credit_api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionRequest {
    private String CustomerId;
    private Long amount;
    private String approvalStatus;
    private String timeToLoan;

}
