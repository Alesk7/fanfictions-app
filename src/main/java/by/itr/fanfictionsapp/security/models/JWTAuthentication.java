package by.itr.fanfictionsapp.security.models;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class JWTAuthentication implements Authentication {

    private UserAccountDetails userDetails;
    private final Serializable credentials;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private boolean isAuthenticated;

    public JWTAuthentication(String token){
        this.credentials = token;
    }

    public JWTAuthentication(UserAccountDetails userDetails){
        this.credentials = null;
        this.userDetails = userDetails;
        this.grantedAuthorities = userDetails.getAuthorities();
        this.isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Once created you cannot set this token to authenticated.");
        }
        this.isAuthenticated = false;
    }

    @Override
    public String getName() {
        return Objects.isNull(this.userDetails) ? null : this.userDetails.getUsername();
    }
}
