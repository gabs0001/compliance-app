package com.esg.compliance.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "TB_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER"
    )
    @SequenceGenerator(
            name = "SEQ_USER",
            sequenceName = "SEQ_USER",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    public static User create(String username, String encodedPassword) {
        if(username == null || username.isBlank())
            throw new RuntimeException("Username cannot be null or blank");

        if(encodedPassword == null || encodedPassword.isBlank())
            throw new RuntimeException("Password cannot be null or blank");

        User user = new User();
        user.username = username;
        user.password = encodedPassword;

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}