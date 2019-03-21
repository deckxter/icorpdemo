package com.deckxter.icorpdemo.service;

import com.deckxter.icorpdemo.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void save(CustomerDTO customerDTO);
    List<CustomerDTO> findAll();
}
