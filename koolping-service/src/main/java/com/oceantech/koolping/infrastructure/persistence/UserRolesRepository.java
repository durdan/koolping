package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository  extends CrudRepository<UserRoles, Long> {
}
