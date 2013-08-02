package com.oceantech.koolping.infrastructure.persistence;


import com.oceantech.koolping.AbstractKoolpingRepositoryTest;
import com.oceantech.koolping.domain.model.identity.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class UserIdRepositoryTest extends AbstractKoolpingRepositoryTest {

    @Autowired
    private UserIdRepository userIdRepository;

    @Test
    public void shouldGenerateNextIdentity() throws Exception {
        UserId id = userIdRepository.nextIdentity();
        assertThat(id, is(notNullValue()));
    }
}
