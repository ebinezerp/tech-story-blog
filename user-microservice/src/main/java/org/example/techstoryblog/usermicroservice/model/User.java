package org.example.techstoryblog.usermicroservice.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotNull
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(nullable = false, unique = true)
    @NotNull
    private String mobile;

    @Column(nullable = false)
    @NotNull
    private Boolean isActive;

    public User(String username, String password, String email, String mobile, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.isActive = isActive;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User user = new User();
        user.id = this.id;
        user.username = this.username;
        user.password = this.password;
        user.email = this.email;
        user.mobile = this.mobile;
        user.isActive = this.isActive;
        return user;
    }
}
