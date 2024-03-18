package com.enigma.load_credit_api.entity;

import com.enigma.load_credit_api.constant.ConstantTable;
import com.enigma.load_credit_api.dto.response.LoanTransactionDetailResponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.LOAN_TRANSACTION_DETAIL)
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_trans_id")
    private LoanTransaction loanTransaction;

    @Column(name = "loan_Amount")
    private Long loanAmount;
}
