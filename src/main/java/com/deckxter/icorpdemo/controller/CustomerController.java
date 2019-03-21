package com.deckxter.icorpdemo.controller;

import com.deckxter.icorpdemo.dto.CustomerDTO;
import com.deckxter.icorpdemo.dto.KpiDto;
import com.deckxter.icorpdemo.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        logger.debug("saveCustomer");
        customerService.save(customerDTO);
    }

    @GetMapping(path = "")
    public List<CustomerDTO> findAllCustomers() {
        logger.debug("findAllCustomers");
        return customerService.findAll();
    }

    @GetMapping(path = "/kpi")
    public KpiDto getKpiCustomers() {
        logger.debug("getKpiCustomers");
        return customerService.getKpiCustomers();
    }
}
