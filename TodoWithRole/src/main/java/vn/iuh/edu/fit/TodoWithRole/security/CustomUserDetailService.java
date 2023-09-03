package vn.iuh.edu.fit.TodoWithRole.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.iuh.edu.fit.TodoWithRole.entities.Role;
import vn.iuh.edu.fit.TodoWithRole.entities.User;
import vn.iuh.edu.fit.TodoWithRole.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Getter
@Setter
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not exists by Username or Email")
        );
        System.out.println(user);
        Set<GrantedAuthority> grantedAuthoritySet =user.getRoles().stream().
                map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User
                (usernameOrEmail,user.getPassword(),grantedAuthoritySet);}
}
