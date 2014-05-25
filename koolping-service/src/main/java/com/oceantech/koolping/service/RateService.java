package com.oceantech.koolping.service;

import com.oceantech.koolping.domain.Rate;

/**
 * @author Sanjoy Roy
 */
public interface RateService {
    Rate findById(final Long id);
    Rate createOrUpdate(final Rate rate);
    void delete(final Rate rate);
}
