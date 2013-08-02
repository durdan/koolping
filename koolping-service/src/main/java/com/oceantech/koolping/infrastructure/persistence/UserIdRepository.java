package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.identity.UserId;

public interface UserIdRepository {
    UserId nextIdentity();
}
