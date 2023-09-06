package ua.chernonog.working.antifraud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import ua.chernonog.working.antifraud.model.emuns.Role;

import java.time.LocalDate;

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
    @Enumerated(EnumType.STRING)
    Role role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate localDate;
}

