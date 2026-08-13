package com.matthew050102.wareflow.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_data")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String passwordHashed;

}
