package com.example.vocabularymanagementsystem.dto;

import com.example.vocabularymanagementsystem.entity.Learner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public record CustomUserDetails(Learner learner) implements UserDetails {

    public CustomUserDetails(Learner learner) {
        this.learner = learner;
    }

    public Learner getLearner() {
        return learner;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return learner.getRoles().stream().map(
                r -> new SimpleGrantedAuthority(r.getRolename().name())
        ).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return learner.getPassword();
    }

    @Override
    public String getUsername() {
        return learner.getUsername();
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}