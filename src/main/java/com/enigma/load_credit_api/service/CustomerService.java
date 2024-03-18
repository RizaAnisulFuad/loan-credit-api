package com.enigma.load_credit_api.service;

import com.enigma.load_credit_api.dto.request.UpdateCustomerRequest;
import com.enigma.load_credit_api.dto.response.CustomerResponse;
import com.enigma.load_credit_api.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<CustomerResponse> getCustomers (String customer);
    CustomerResponse updateCustomer (UpdateCustomerRequest customer);
    void deleteById(String id);
    void updateStatusById(String id, Boolean status);
}
