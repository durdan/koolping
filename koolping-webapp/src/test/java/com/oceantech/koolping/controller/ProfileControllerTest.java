package com.oceantech.koolping.controller;


import com.oceantech.koolping.domain.model.identity.User;
import com.oceantech.koolping.domain.model.identity.UserId;
import com.oceantech.koolping.infrastructure.persistence.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.servlet.View;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class ProfileControllerTest {

    private static final String EXPECTED_PROFILE_VIEW = "profile";

    @InjectMocks
    private ProfileController controller;
    @Mock
    private UserRepository repository;
    @Mock
    private View mockView;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() throws Exception {
        controller = new ProfileController();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    public void shouldShowProfilePage() throws Exception {
        User user =  getFakeUser();
        Page<User> users = new PageImpl<User>(Arrays.asList(user));
        when(repository.findByUserId(any(UserId.class), any(Pageable.class))).thenReturn(users);

        this.mockMvc.perform(get("/profile/testUserId"))
                .andExpect(model().attribute("user", user))
                .andExpect(view().name(containsString(EXPECTED_PROFILE_VIEW)))
                .andExpect(status().isOk());

        verify(repository).findByUserId(any(UserId.class), any(Pageable.class));
    }

    private User getFakeUser(){
        User user = new User();
        user.setUserId(new UserId("testUserId"));
        user.setUserName("testUser");
        return user;
    }
}
