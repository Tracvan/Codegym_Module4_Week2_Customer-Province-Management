package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends  IGenerateService<Customer>{
    Page<Customer> findAll(Pageable pageable);

}
