package com.enigma.load_credit_api.repository;

import com.enigma.load_credit_api.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {
}
