package com.realtech.cloudschool.controller;



import com.realtech.cloudschool.model.User;
import com.realtech.cloudschool.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.servlet.View;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

public class RegistrationControllerTest {

    private static final String EXPECTED_HOME_VIEW = "home";
    private static final String EXPECTED_LOGIN_VIEW = "login";

    @InjectMocks
    private RegistrationController controller;
    @Mock
    private View mockView;
    @Mock
    private UserRepository mockRepository;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        controller = new RegistrationController();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    public void shouldRegisterUser() throws Exception{
        User user = getFakeUser();
        User savedUser = getFakeUser();
        savedUser.setId(101L);
        when(mockRepository.save(user)).thenReturn(savedUser);

        this.mockMvc.perform(
                post("/register")
                    .param("username", user.getUsername())
                    .param("password", user.getPassword())
                    .param("firstname", user.getFirstname())
                    .param("lastname", user.getLastname())
                    .param("email", user.getEmail())
                    .param("interests", user.getInterests()))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name(containsString(EXPECTED_HOME_VIEW)));

        verify(mockRepository).save(any(User.class));
        verify(mockView).render(any(Map.class), any(HttpServletRequest.class), any(HttpServletResponse.class));
        verifyNoMoreInteractions(mockRepository, mockView);

    }

    private User getFakeUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setFirstname("testFirstname");
        user.setLastname("testLastname");
        user.setEmail("testEmail@abc.com");
        user.setInterests("test interests");
        return user;
    }
}