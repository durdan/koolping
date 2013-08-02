package com.oceantech.koolping.infrastructure.persistence.impl;


import com.oceantech.koolping.domain.model.identity.UserId;
import com.oceantech.koolping.infrastructure.persistence.UserIdRepository;

import java.util.UUID;


public class UserIdRepositoryImpl implements UserIdRepository {
    @Override
    public UserId nextIdentity() {
        return new UserId(UUID.randomUUID().toString().toUpperCase());
    }
}
