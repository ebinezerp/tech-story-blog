package org.example.techstoryblog.usermicroservice.controller;

import org.example.techstoryblog.usermicroservice.servcie.UserService;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

}
