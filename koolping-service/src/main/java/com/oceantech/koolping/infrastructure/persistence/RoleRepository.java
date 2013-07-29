package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
