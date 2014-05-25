package com.oceantech.koolping.service.impl;

import com.oceantech.koolping.annotation.ControlService;
import com.oceantech.koolping.domain.Rate;
import com.oceantech.koolping.repository.RateRepository;
import com.oceantech.koolping.service.RateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * @author Sanjoy Roy
 */

@ControlService
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate findById(final Long id) {
        Rate rate = rateRepository.findOne(id);
        if(rate == null){
            throw new DataRetrievalFailureException("Id [ "+ id +" ] id not valid");
        }
        return rate;
    }

    @Override
    public Rate createOrUpdate(final Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void delete(final Rate rate) {
        rateRepository.delete(rate);
    }
}
