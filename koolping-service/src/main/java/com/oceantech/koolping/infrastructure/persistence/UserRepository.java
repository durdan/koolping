package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.User;

import com.oceantech.koolping.domain.model.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Long> {

    Page<User> findByUsername(String username, Pageable pageable);

    Page<User> findByUserId(UserId userId, Pageable pageable);
}
