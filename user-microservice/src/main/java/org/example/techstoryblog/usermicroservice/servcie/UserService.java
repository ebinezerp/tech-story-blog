package org.example.techstoryblog.usermicroservice.servcie;

import org.example.techstoryblog.usermicroservice.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> get();

    User get(long id);

    User update(User user);

    User get(String usernameOrEmail, String password);

}
