package org.example.techstoryblog.usermicroservice.repository;

import org.example.techstoryblog.usermicroservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("ebinezer", "Assd@1234", "ebinezer@gmail.com", "9494216610", true);
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser(){
        Assertions.assertNotNull(userRepository.save(user));
        Assertions.assertNotNull(user.getId());
    }

    @Test
    public void testSaveUserWithNullUsername(){
        user.setUsername(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testSaveUserWithNullPassword(){
        user.setPassword(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testSaveUserWithNullEmail(){
        user.setEmail(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testSaveUserWithNullMobile(){
        user.setMobile(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testSaveUserWithNullIsActive(){
        user.setIsActive(null);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }


    @Test
    public void testSaveUserWithSameUsername() throws CloneNotSupportedException {
        User duplicate = user.clone();
        userRepository.save(user);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(duplicate));
    }

    @Test
    public void testGetUserWithUserId(){
        userRepository.save(user);
        Assertions.assertNotNull(userRepository.findById(user.getId()).orElse(null));
    }

    @Test
    public void testGetUserWithIdWhichIsNotExistedInDB(){
        Assertions.assertNull(userRepository.findById(101L).orElse(null));
    }

    @Test
    public void testUpdateUser(){
        userRepository.save(user);
        User user = userRepository.findById(this.user.getId()).orElse(null);
        user.setEmail("ravi@gmail.com");
        Assertions.assertNotNull(userRepository.save(user));
    }

    @Test
    public void testFindByUsernameAndPasswordOrEmailAndPassword(){
        userRepository.save(user);
        Assertions.assertNotNull(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getUsername(), user.getPassword(), user.getEmail(), user.getPassword()).orElse(null));
    }

    @Test
    public void testFindByUsernameAndPasswordOrEmailAndPasswordWithCorrectUsernameAndPassword(){
        userRepository.save(user);
        Assertions.assertNotNull(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getUsername(), user.getPassword(), "","").orElse(null));
    }

    @Test
    public void testFindByUsernameAndPasswordOrEmailAndPasswordWithCorrectEmailAndPassword(){
        userRepository.save(user);
        Assertions.assertNotNull(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getEmail(),user.getPassword(), user.getEmail(),user.getPassword()).orElse(null));
    }

    @Test
    public void testFindByUsernameAndPasswordOrEmailAndPasswordWithoutData(){
        Assertions.assertNull(userRepository.findByUsernameAndPasswordOrEmailAndPassword(user.getUsername(), user.getPassword(), user.getEmail(), user.getPassword()).orElse(null));
    }
}
