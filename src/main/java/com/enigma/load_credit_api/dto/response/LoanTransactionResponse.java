package com.enigma.load_credit_api.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanTransactionResponse {
    private String customerId;
    private Long amount;
    private String approvalStatus;
    private List<LoanTransactionDetailResponse> loanTransactionDetail;
}
