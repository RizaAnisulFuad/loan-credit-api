package com.enigma.load_credit_api.dto.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionDetailRequest {
    private String loanTransactionId;
    private Integer interestRate;
}
