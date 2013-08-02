package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.identity.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class UserRolesRepositoryIntegrationTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private UserRolesRepository repository;

    @Test
    public void shouldSaveUserRoles() {
        UserRoles userRoles = repository.save(new UserRoles());
        assertThat(userRoles.getUserRoleId(), is(notNullValue()));
    }
}
