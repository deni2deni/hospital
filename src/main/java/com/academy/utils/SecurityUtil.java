package com.academy.utils;

import com.academy.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityUtil {

    public Collection<? extends GrantedAuthority> getUserRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public boolean hasRole(Role role) {
        return getUserRoles().stream().anyMatch(authority -> authority.getAuthority().equals(role.name()));
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
