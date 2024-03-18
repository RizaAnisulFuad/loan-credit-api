package com.enigma.load_credit_api.service.impl;

import com.enigma.load_credit_api.dto.request.LoanTransactionDetailRequest;
import com.enigma.load_credit_api.dto.request.LoanTransactionRequest;
import com.enigma.load_credit_api.dto.request.SearchLoanTransactionRequest;
import com.enigma.load_credit_api.dto.response.CustomerResponse;
import com.enigma.load_credit_api.dto.response.LoanTransactionDetailResponse;
import com.enigma.load_credit_api.dto.response.LoanTransactionResponse;
import com.enigma.load_credit_api.entity.Customer;
import com.enigma.load_credit_api.entity.LoanTransaction;
import com.enigma.load_credit_api.repository.LoanTransactionRepository;
import com.enigma.load_credit_api.service.CustomerService;
import com.enigma.load_credit_api.service.LoanTransactionDetailService;
import com.enigma.load_credit_api.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final CustomerService customerService;
    private final LoanTransactionService loanTransactionService;
    private final LoanTransactionDetailService loanTransactionDetailService;
    private final LoanTransactionRepository loanTransactionRepository;

    @Override
    public LoanTransactionResponse requestLoan(LoanTransactionRequest request) {
        CustomerResponse customerResponse = (CustomerResponse) customerService.getCustomers(request.getCustomerId());
        Customer customer = Customer.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .gender(customerResponse.getGender())
                .phoneNumber(customerResponse.getPhoneNumber())
                .address(customerResponse.getAddress())
                .birthDate(customerResponse.getBirthDate())
                .status(customerResponse.getStatus())
                .build();

        if (request.getAmount() > 100000000) throw new DataIntegrityViolationException("this amount value is greater than the limit");

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .customer(customer)
                .build();
        loanTransactionRepository.saveAndFlush(loanTransaction);

        return LoanTransactionResponse.builder()
                .customerId(loanTransaction.getId())
                .amount(loanTransaction.getLoanAmount())
                .approvalStatus(loanTransaction.getApprovalStatus())
                .build();
    }

    @Override
    public Page<LoanTransactionResponse> getAll(SearchLoanTransactionRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<LoanTransaction> loanTransactions = loanTransactionRepository.findAll(pageable);

        return loanTransactions.map(trx -> {
            List<LoanTransactionDetailResponse> trxDetailResponses = trx.getLoanTransactionDetail().stream().map(detail -> LoanTransactionDetailResponse.builder()
                    .id(trx.getId())
                    .loanAmount(trx.getLoanAmount())
                    .timeToLoan(trx.getTransaDate())
                    .build()).toList();

            return LoanTransactionResponse.builder()
                    .customerId(trx.getCustomer().getId())
                    .amount(trx.getLoanAmount())
                    .approvalStatus(trx.getApprovalStatus())
                    .build();
        });
    }

    @Override
    public LoanTransactionResponse approveLoan(LoanTransactionDetailRequest request) {
        LoanTransaction loanTransaction = loanTransactionRepository.findById(request.getLoanTransactionId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DATA NOT FOUND"));
        LoanTransaction.setApprovalStatus("APPROVE");



    }

    @Override
    public LoanTransactionResponse rejectLoan(LoanTransactionDetailRequest request) {
        return null;
    }
}
