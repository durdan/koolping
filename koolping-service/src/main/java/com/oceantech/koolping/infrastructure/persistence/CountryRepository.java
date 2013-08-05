package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.domain.model.common.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 30/07/13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public interface CountryRepository extends CrudRepository<Country,String>  {
}
