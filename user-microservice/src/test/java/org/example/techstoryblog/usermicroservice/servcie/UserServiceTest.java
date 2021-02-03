package org.example.techstoryblog.usermicroservice.servcie;

import org.example.techstoryblog.usermicroservice.model.User;
import org.example.techstoryblog.usermicroservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("ebinezer", "Assd@1234", "ebinezer@gmail.com", "9494216610", true);
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertNotNull(userService.createUser(user));
    }

    @Test
    public void testSaveUserWithNullUsername(){
        user.setUsername(null);
        Mockito.when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertNull(userService.createUser(user));
    }

    @Test
    public void testSaveUserWithDuplicateUsername(){
        Mockito.when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertNull(userService.createUser(user));
    }

    @Test
    public void testGetUserWithId(){
        user.setId(101L);
        Mockito.when(userRepository.findById(101L)).thenReturn(Optional.of(user));
        Assertions.assertNotNull(userService.get(101L));
    }

    @Test
    public void testGetUserWithIdWhichIsNotPresent(){
        Mockito.when(userRepository.findById(101L)).thenReturn(Optional.empty());
        Assertions.assertNull(userService.get(101L));
    }

    @Test
    public void testUpdateUser(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertNotNull(userService.update(user));
    }


    @Test
    public void testUpdateUserWithNullusername(){
        user.setUsername(null);
        Mockito.when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);
        Assertions.assertNull(userService.update(user));
    }

    @Test
    public void testGetUserWithEmailAndPassword(){
        Mockito.when(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getEmail(),user.getPassword(), user.getEmail(), user.getPassword())).thenReturn(Optional.ofNullable(user));
        Assertions.assertNotNull(userService.get(user.getEmail(), user.getPassword()));
    }

    @Test
    public void testGetUserWithUsernameAndPassword(){
        Mockito.when(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getUsername(), user.getPassword(),user.getUsername(),user.getPassword())).thenReturn(Optional.of(user));
        Assertions.assertNotNull(userService.get(user.getUsername(), user.getPassword()));
    }


    @Test
    public void testGetAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(
                new User(), new User()
        ));
        List<User> userList = userService.get();
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(2, userList.size());

    }

}
