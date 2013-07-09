package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.domain.model.UserId;

public interface UserIdRepository {
    UserId nextIdentity();
}
