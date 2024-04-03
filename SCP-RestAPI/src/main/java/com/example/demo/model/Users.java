package com.example.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
public class Users implements UserDetails {
    @Id
    private String id;
    @Getter
    @Setter
    private String username;
    @Getter
    private String password;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private Set<GrantedAuthority> authorities;
    @Getter
    @Setter
    private final boolean isAccountNonExpired;
    @Getter
    @Setter
    private final boolean isAccountNonLocked;
    @Getter
    @Setter
    private final boolean isCredentialsNonExpired;
    @Getter
    @Setter
    private final boolean isEnabled;


    public Users(String username, String password, String role, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        setPassword(password);
        this.role = role;
    }

    //Metod för att hasha lösen med BCryptPasswordEncoder
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    //metoder som måste implementeras pga UserDetails och CredentialsContainer gränssnitten
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Method to set authorities based on user's role
    @Override
    public boolean isEnabled() {
        return true;
    }

}
