package com.paygo.paygo.impl;

import com.paygo.paygo.dto.CustomerDto;
import com.paygo.paygo.entity.Customer;
import com.paygo.paygo.entity.User;
import com.paygo.paygo.entity.UserProfile;
import com.paygo.paygo.repository.CustomerRepository;
import com.paygo.paygo.repository.UserProfileRepository;
import com.paygo.paygo.service.CustomerService;
import com.paygo.paygo.utils.AuthenticatedUser;
import com.paygo.paygo.utils.Core;
import com.paygo.paygo.utils.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Autowired
    private DataResponse dataResponse;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private Core core;

    @Override
    public ResponseEntity<?> createCustomer(CustomerDto customer) {

        User authUser = authenticatedUser.auth();
        if(customerRepository.existsByEmail(customer.getEmail())){
            Map<Object, Object> error = new HashMap<>();
            error.put("message", "Email has already been registered");
            return dataResponse.dataResponse("02", "fail", error, HttpStatus.BAD_REQUEST);
        } else if (customerRepository.existsByIdNo(customer.getIdNo())) {
            Map<Object, Object> error = new HashMap<>();
            error.put("message", "ID Number has been registered on our system");
            return dataResponse.dataResponse("02", "fail", error, HttpStatus.BAD_REQUEST);

        } else if (customerRepository.existsByMnemonic(customer.getMnemonic()) || customer.getMnemonic().length() < 4 || customer.getMnemonic().length() > 6) {
            Map<Object, Object> error = new HashMap<>();
            error.put("message", "Mnemonic must be unique and must be minimum of 4 character and maximum of 6 character");
            return dataResponse.dataResponse("02", "fail", error, HttpStatus.BAD_REQUEST);
        } else if (customerRepository.existsByMobileNo(customer.getMobileNo())) {
            Map<Object, Object> error = new HashMap<>();
            error.put("message", "Mobile NO has already been registered");
            return dataResponse.dataResponse("02", "fail", error, HttpStatus.BAD_REQUEST);
        }else {
            UserProfile userProfile = userProfileRepository.findUserProfileByUserId(authUser.getId());
            Customer newCustomer = getCustomer(customer, userProfile);
            customerRepository.save(newCustomer);

            Map<Object, Object> success = new HashMap<>();
            success.put("message", "Customer created successfully");
            return dataResponse.dataResponse("00", "success", success, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> fetchCustomer(String cif) {
        Customer customer = customerRepository.findCustomerByCif(cif);
        if(!Objects.equals(customer, null)){
            return dataResponse.dataResponse("00", "success", customer, HttpStatus.OK);
        }else {
            Map<Object, Object> error = new HashMap<>();
            error.put("message", "Customer record not found");
            return dataResponse.dataResponse("00", "success", error, HttpStatus.OK);
        }
    }

    private Customer getCustomer(CustomerDto customer, UserProfile userProfile) {
        Customer newCustomer = new Customer();
        newCustomer.setCreatedBy(String.format("%s %s", userProfile.getFirstName().toUpperCase(), userProfile.getLastName().toUpperCase()));
        newCustomer.setCif(core.getCif());
        newCustomer.setCountry(customer.getCountry().toUpperCase());
        newCustomer.setAddress(customer.getAddress().toUpperCase());
        newCustomer.setEmail(customer.getEmail().toUpperCase());
        newCustomer.setIdNo(customer.getIdNo().toUpperCase());
        newCustomer.setIdType(customer.getIdType().toUpperCase());
        newCustomer.setState(customer.getState().toUpperCase());
        newCustomer.setMnemonic(customer.getMnemonic().toUpperCase());
        newCustomer.setAltMobileNo(customer.getAltMobileNo().toUpperCase());
        newCustomer.setFirstName(customer.getFirstName().toUpperCase());
        newCustomer.setLastName(customer.getLastName().toUpperCase());
        newCustomer.setMaidenName(customer.getMaidenName().toUpperCase());
        newCustomer.setMobileNo(customer.getMobileNo().toUpperCase());
        newCustomer.setMiddleName(customer.getMiddleName());
        return newCustomer;
    }
}
