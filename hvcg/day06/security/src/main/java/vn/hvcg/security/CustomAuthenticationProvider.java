package vn.hvcg.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.hvcg.security.domain.User;
import vn.hvcg.security.domain.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Optional<User> userOp = userRepository.findByUsername(username);

        if (!userOp.isPresent()) {
            throw new UsernameNotFoundException("Username Not Found");
        }

        if (passwordEncoder.matches(password, userOp.get().getPassword())) {
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), mapAuthorities(userOp.get().getRoles()));
            result.setDetails(authentication.getDetails());
            return result;
        } else {
            return null;
        }
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(String roles) {
        if (roles == null || roles.length() == 0) {
            return new ArrayList<>();
        }
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
