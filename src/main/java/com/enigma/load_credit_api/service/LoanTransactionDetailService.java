package com.enigma.load_credit_api.service;

import com.enigma.load_credit_api.entity.LoanTransactionDetail;

import java.util.List;

public interface LoanTransactionDetailService {
    List<LoanTransactionDetail> createBulk (List<LoanTransactionDetail> loanTransactionDetails);
}
