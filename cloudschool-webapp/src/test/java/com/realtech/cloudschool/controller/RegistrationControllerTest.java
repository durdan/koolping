package com.realtech.cloudschool.controller;

import com.realtech.cloudschool.command.UserCommand;
import com.realtech.cloudschool.identityaccess.domain.model.Email;
import com.realtech.cloudschool.identityaccess.domain.model.User;
import com.realtech.cloudschool.identityaccess.domain.model.UserId;
import com.realtech.cloudschool.identityaccess.domain.model.UserRoles;
import com.realtech.cloudschool.identityaccess.infrastructure.persistence.UserIdRepository;
import com.realtech.cloudschool.identityaccess.infrastructure.persistence.UserRepository;
import com.realtech.cloudschool.identityaccess.infrastructure.persistence.UserRolesRepository;
import com.realtech.cloudschool.validator.UserCommandValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.servlet.View;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


import static org.hamcrest.CoreMatchers.is;
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
    private static final String EXPECTED_REDIRECT_VIEW = "login";

    @InjectMocks
    private RegistrationController controller;
    @Mock
    private View mockView;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserIdRepository mockUserIdRepository;
    @Mock
    private UserRolesRepository mockRolesRepository;
    @Spy
    private UserCommandValidator validator;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        controller = new RegistrationController();
        validator = new UserCommandValidator();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    public void shouldFailToRegisterUserWhenValidationErrorOccurs() throws Exception {
        this.mockMvc.perform(
                post("/register")
                        .param("username", "testUsername")
                        .param("password", "testPassword")
                        .param("lastname", "testLastname")
                        .param("email", "testEmail@abc.com"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors())
                .andExpect(model().attribute("userCommand", is(UserCommand.class)))
                .andExpect(view().name(containsString(EXPECTED_REDIRECT_VIEW)));
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        User user = getFakeUser();
        User savedUser = getFakeUser();
        savedUser.setId(101L);
        when(mockUserIdRepository.nextIdentity()).thenReturn(getFakeUserId());
        when(mockUserRepository.save(any(User.class))).thenReturn(savedUser);
        UserRoles savedRoles = getFakeRoles();
        savedRoles.setUserRoleId(1001L);
        when(mockRolesRepository.save(any(UserRoles.class))).thenReturn(savedRoles);

        this.mockMvc.perform(
                post("/register")
                    .param("username", "testUsername")
                    .param("password", "testPassword")
                    .param("firstname", "testFirstname")
                    .param("lastname", "testLastname")
                    .param("email", "testEmail@abc.com")
                    .param("interests", user.getInterests()))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name(containsString(EXPECTED_HOME_VIEW)));

        verify(mockUserIdRepository).nextIdentity();
        verify(mockUserRepository).save(any(User.class));
        verify(mockRolesRepository).save(any(UserRoles.class));
        verify(mockView).render(any(Map.class), any(HttpServletRequest.class), any(HttpServletResponse.class));
        verifyNoMoreInteractions(mockUserIdRepository, mockUserRepository, mockRolesRepository, mockView);

    }

    private User getFakeUser(){
        User user = new User();
        user.setUsername("testUsername");
        user.setPassword("$1$54%as56w$UgWV0I9YO9GyYfAqGmW7w/");
        user.setFirstname("testFirstname");
        user.setLastname("testLastname");
        Email email = new Email("testEmail@abc.com");
        user.setEmail(email);
        user.setInterests("test interests");
        user.setEnabled(true);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return user;
    }

    private UserId getFakeUserId(){
        return new UserId(UUID.randomUUID().toString().toUpperCase());
    }

    private UserRoles getFakeRoles(){
        UserRoles roles = new UserRoles();
        roles.setUserId(101L);
        roles.setAuthority("ROLE_USER");
        return roles;
    }
}
