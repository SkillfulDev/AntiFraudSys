package ua.chernonog.working.antifraud.model.emuns;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR,MERCHANT,SUPPORT;

    @Override
    public String getAuthority() {
        return "ROLE_"+ name();
    }

}
