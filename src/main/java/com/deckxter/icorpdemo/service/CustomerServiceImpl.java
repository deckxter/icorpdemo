package com.deckxter.icorpdemo.service;

import com.deckxter.icorpdemo.dto.CustomerDTO;
import com.deckxter.icorpdemo.entity.CustomerEntity;
import com.deckxter.icorpdemo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(CustomerDTO customerDTO) {
        logger.debug("Saving customer: " +customerDTO);
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for(CustomerEntity customerEntity : customerEntities) {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerEntity, customerDTO);
            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

    private Timestamp calculateDeathDate(Timestamp birthday, Integer years) {

        return null;
    }

    private int giveMeExtraYears(Integer currentYears) {
        //Max life: 100 years
        return 100 - currentYears;
    }
}
