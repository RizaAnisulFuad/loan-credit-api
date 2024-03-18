package com.enigma.load_credit_api.service;

import com.enigma.load_credit_api.dto.request.LoanTransactionDetailRequest;
import com.enigma.load_credit_api.dto.request.LoanTransactionRequest;
import com.enigma.load_credit_api.dto.request.SearchLoanTransactionRequest;
import com.enigma.load_credit_api.dto.response.LoanTransactionResponse;
import org.springframework.data.domain.Page;

public interface LoanTransactionService {
    LoanTransactionResponse requestLoan(LoanTransactionRequest request);
    Page<LoanTransactionResponse> getAll(SearchLoanTransactionRequest request);
    LoanTransactionResponse approveLoan(LoanTransactionDetailRequest request);
    LoanTransactionResponse rejectLoan(LoanTransactionDetailRequest request);
}
