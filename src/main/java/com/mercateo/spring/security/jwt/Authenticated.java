package com.mercateo.spring.security.jwt;

import java.util.Collection;

import io.vavr.collection.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.vavr.collection.Map;
import io.vavr.control.Option;

public class Authenticated implements UserDetails {

    private final Long id;

    private final String username;

    private final String token;

    private final List<? extends GrantedAuthority> authorities;

    private final Map<String, String> claims;

    public Authenticated(Long id, String username, String token, List<? extends GrantedAuthority> authorities,
            Map<String, String> claims) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
        this.claims = claims;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.toJavaList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    public Option<String> getClaim(String key) {
        return claims.get(key);
    }

    public static Authenticated fromContext() {
        return (Authenticated) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
