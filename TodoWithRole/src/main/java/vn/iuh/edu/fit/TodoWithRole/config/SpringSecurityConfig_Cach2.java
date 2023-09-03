package vn.iuh.edu.fit.TodoWithRole.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * B1 them @EnableMethodSecurity  trc class
 * B2: tai cac method API them @PreAuthorize xem vidu tai controller
 * note: bất kể method nào cũng phải có @ProAuthorize vì khi run lên bắt buộc
 * đăng nhập mà có tài không đc phân quyên thì đăng nhập làm gì  vìdđ cài đặt là authorize.anyRequest().authenticated();
 * tất cả request phải đăng xác thực
 * :)
 */

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig_Cach2 {

    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
// vi da co table roif khong tao bean nay neu co se bi loi!
//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        UserDetails manager = User.builder().username("manager").password(passwordEncoder().encode("manager")).roles("MANAGER").build();
//        return new InMemoryUserDetailsManager(user, admin, manager);
//    }
}
