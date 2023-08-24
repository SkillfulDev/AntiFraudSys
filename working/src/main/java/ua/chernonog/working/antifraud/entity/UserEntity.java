package ua.chernonog.working.antifraud.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import ua.chernonog.working.antifraud.model.emuns.Role;

//@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String username;

    String password;
    @Enumerated
    Role role;
}

