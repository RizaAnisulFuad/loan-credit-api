package com.enigma.load_credit_api.Specification;

import com.enigma.load_credit_api.dto.request.SearchCustomerRequest;
import com.enigma.load_credit_api.entity.Customer;
import com.enigma.load_credit_api.utils.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getGender() != null) {
                Predicate genderPredicate = cb.like(cb.lower(root.get("gender")), "%" + request.getGender().toLowerCase() + "%");
                predicates.add(genderPredicate);
            }

            if (request.getPhoneNumber() != null) {
                Predicate phonePredicate = cb.equal(root.get("phoneNumber"), request.getPhoneNumber());
                predicates.add(phonePredicate);
            }

            if (request.getAddress() != null) {
                Predicate addressPredicate = cb.equal(root.get("phoneNumber"), request.getAddress());
                predicates.add(addressPredicate);
            }

            if (request.getBirthDate() != null) {
                Date tempDate = DateUtil.parseDate(request.getBirthDate(), "yyyy-MM-dd");
                Predicate birthDatePredicate = cb.equal(root.get("birthDate"), tempDate);
                predicates.add(birthDatePredicate);
            }

            if (request.getStatus() != null) {
                Predicate statusPredicate = cb.equal(root.get("status"), request.getStatus());
                predicates.add(statusPredicate);
            }

            return cq.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

    }
}
