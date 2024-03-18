package com.enigma.load_credit_api.repository;

import com.enigma.load_credit_api.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {

}
