package com.oceantech.koolping.infrastructure.persistence;

import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.Role;
import com.oceantech.koolping.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created with IntelliJ IDEA.
 * User: durdan
 * Date: 25/07/13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class RoleRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @Test
    public void shouldSaveRoles() {
        User user = new User("username");
        Role role = repository.save(new Role(1,user));
        assertThat(role.getId(), is(notNullValue()));
    }
}
