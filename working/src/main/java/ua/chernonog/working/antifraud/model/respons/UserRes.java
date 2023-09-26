package ua.chernonog.working.antifraud.model.respons;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lombok.experimental.Accessors;
import ua.chernonog.working.antifraud.model.emuns.Role;
import ua.chernonog.working.antifraud.config.View;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    @JsonView(View.IpTop.class)
    Long id;
    @JsonView(View.IpTop.class)
    String name;
    @JsonView(View.IpTop.class)
    String username;

    Role role;

    String status;


}
