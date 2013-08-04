package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.common.Country;
import org.springframework.data.repository.CrudRepository;


public interface CountryRepository extends CrudRepository<Country,String>  {
}
