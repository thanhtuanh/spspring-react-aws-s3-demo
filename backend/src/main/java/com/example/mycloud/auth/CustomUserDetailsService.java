package com.example.mycloud.auth;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 👉 hier evtl. aus DB laden
        if ("admin".equals(username)) {
            return new User("admin", "{noop}password", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }
        throw new UsernameNotFoundException("Benutzer nicht gefunden");
    }
}
