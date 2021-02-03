package org.example.techstoryblog.usermicroservice.servcie;

import org.example.techstoryblog.usermicroservice.model.User;
import org.example.techstoryblog.usermicroservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            logger.info(e.getMessage());
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public User get(long id) {
       return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            logger.info(e.getMessage());
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public User get(String usernameOrEmail, String password) {
        return userRepository.findByUsernameAndPasswordOrEmailAndPassword(usernameOrEmail, password, usernameOrEmail, password).orElse(null);
    }

}
