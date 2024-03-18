package com.enigma.load_credit_api.controller;

import com.enigma.load_credit_api.constant.APIUrl;
import com.enigma.load_credit_api.constant.ResponseMessage;
import com.enigma.load_credit_api.dto.request.SearchCustomerRequest;
import com.enigma.load_credit_api.dto.response.CommonResponse;
import com.enigma.load_credit_api.dto.response.CustomerResponse;
import com.enigma.load_credit_api.entity.Customer;
import com.enigma.load_credit_api.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "birthDate", required = false) @JsonFormat(pattern = "dd-MM-yyy") String birthDate,
            @RequestParam(name = "status", required = false) Boolean status
    ){
        SearchCustomerRequest request = SearchCustomerRequest.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .address(address)
                .birthDate(birthDate)
                .status(status)
                .build();

        List<CustomerResponse> customers =  customerService.getCustomers(request);

        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customers)
                .build();

        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<String>> updateStatusCustomer(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "status") Boolean status
    ) {
        customerService.updateStatusById(id, status);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<String>> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

}
