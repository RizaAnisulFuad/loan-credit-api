package com.enigma.load_credit_api.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchLoanTransactionRequest {
    private Integer page;
    private Integer size;
}
