package com.enigma.load_credit_api.service.impl;

import com.enigma.load_credit_api.entity.LoanTransactionDetail;
import com.enigma.load_credit_api.service.LoanTransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionDetailServiceImpl implements LoanTransactionDetailService {
    @Override
    public List<LoanTransactionDetail> createBulk(List<LoanTransactionDetail> loanTransactionDetails) {
        return null;
    }
}
