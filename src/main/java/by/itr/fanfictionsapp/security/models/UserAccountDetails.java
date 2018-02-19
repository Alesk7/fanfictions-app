package by.itr.fanfictionsapp.security.models;

import by.itr.fanfictionsapp.models.UserAccount;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserAccountDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean enabled;
    private boolean accountNonLocked;

    public UserAccountDetails(UserAccount user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        this.enabled = user.isEnabled();
        this.accountNonLocked = user.isNonBlocked();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
