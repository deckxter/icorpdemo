package com.deckxter.icorpdemo.service;

import com.deckxter.icorpdemo.dto.CustomerDTO;
import com.deckxter.icorpdemo.dto.KpiDto;
import com.deckxter.icorpdemo.entity.CustomerEntity;
import com.deckxter.icorpdemo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

            Date probableDeathDate = this.calculateDeathDate(customerEntity.getBirthday(), customerEntity.getYears());
            customerDTO.setProbableDeathDate(probableDeathDate);
            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

    @Override
    public KpiDto getKpiCustomers() {
        KpiDto kpiDto = new KpiDto();

        List<CustomerEntity> customers = customerRepository.findAll();

        kpiDto.setAverageYear(this.calculateAverageYear(customers));
        kpiDto.setStandardDeviation(this.calculateStandardDeviation(customers));
        return kpiDto;
    }

    private Date calculateDeathDate(Date birthday, Integer years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        calendar.add(Calendar.YEAR, this.giveMeExtraYears(years));

        return calendar.getTime();
    }

    private int giveMeExtraYears(Integer currentYears) {
        //Max life: 100 years
        return 100 - currentYears;
    }

    private Double calculateAverageYear(List<CustomerEntity> customers) {
        Double totalYears = 0.0;
        for(CustomerEntity customerEntity : customers) {
            totalYears = totalYears + customerEntity.getYears();
        }

        return (totalYears / customers.size());
    }

    private Double calculateStandardDeviation(List<CustomerEntity> customers) {
        Double averageYear = this.calculateAverageYear(customers);
        Double totalDistance = 0.0;

        for(CustomerEntity customerEntity : customers) {
            totalDistance = totalDistance + Math.pow(Math.abs(customerEntity.getYears() - averageYear), 2);
        }

        return Math.sqrt((totalDistance / averageYear));
    }
}
