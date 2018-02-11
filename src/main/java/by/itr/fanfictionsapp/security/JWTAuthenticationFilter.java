package by.itr.fanfictionsapp.security;

import by.itr.fanfictionsapp.security.handlers.RestAuthenticationFailureHandler;
import by.itr.fanfictionsapp.security.models.JWTAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String AUTHENTICATION_HEADER = "Authorization";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(request -> true);
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try{
            String token = Optional.ofNullable(request.getHeader(AUTHENTICATION_HEADER)).orElse(null);
            if(token == null)
                throw new BadCredentialsException("Token not found in request's header.");
            JWTAuthentication authRequest = new JWTAuthentication(token);
            return getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException exception) {
            unsuccessfulAuthentication(request, response, exception);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (this.eventPublisher != null) {
            this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        chain.doFilter(request, response);
    }
}
