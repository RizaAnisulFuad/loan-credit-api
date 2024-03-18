package com.enigma.load_credit_api.service.impl;

import com.enigma.load_credit_api.Specification.CustomerSpecification;
import com.enigma.load_credit_api.constant.ResponseMessage;
import com.enigma.load_credit_api.constant.UserRole;
import com.enigma.load_credit_api.dto.request.UpdateCustomerRequest;
import com.enigma.load_credit_api.dto.response.CustomerResponse;
import com.enigma.load_credit_api.entity.Customer;
import com.enigma.load_credit_api.entity.UserAccount;
import com.enigma.load_credit_api.repository.CustomerRepository;
import com.enigma.load_credit_api.service.CustomerService;
import com.enigma.load_credit_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> getCustomers(String request) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll(specification).stream().map(this::convertCustomerToCustomerResponse).toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse updateCustomer(UpdateCustomerRequest request) {
        Customer currentCustomer = findByIdOrThrowNotFound(request.getId());
        UserAccount userAccount = userService.getByContext();
        List<String> role = userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (!userAccount.getId().equals(currentCustomer.getUserAccount().getId()) || !role.contains(UserRole.ROLE_ADMIN.name()) || !role.contains(UserRole.ROLE_SUPER_ADMIN.name())){
        }

        currentCustomer.setName(request.getName());
        currentCustomer.setGender(request.getGender());
        currentCustomer.setAddress(request.getAddress());
        currentCustomer.setBirthDate(request.getBirthDate());
        currentCustomer.setPhoneNumber(request.getPhoneNumber());
        customerRepository.saveAndFlush(currentCustomer);
        return convertCustomerToCustomerResponse(currentCustomer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusById(String id, Boolean status) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateStatus(id, status);
    }

    private Customer findByIdOrThrowNotFound(String id){
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessage.ERROR_NOT_FOUND));
    }

    private CustomerResponse convertCustomerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .gender(customer.getGender())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .phoneNumber(customer.getPhoneNumber())
                .status(customer.getStatus())
                .userAccountId(customer.getUserAccount().getId())
                .build();
    }
}
