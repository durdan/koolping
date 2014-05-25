package com.oceantech.koolping.repository;

import com.oceantech.koolping.domain.Rate;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sanjoy Roy
 */
@Transactional(propagation = Propagation.MANDATORY, readOnly = false)
public interface RateRepository extends GraphRepository<Rate> {
}
