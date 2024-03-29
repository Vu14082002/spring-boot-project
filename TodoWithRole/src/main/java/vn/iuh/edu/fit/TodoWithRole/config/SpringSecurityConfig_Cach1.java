//package vn.iuh.edu.fit.TodoWithRole.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SpringSecurityConfig_Cach1 {
//
//   @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests((authorize)->{
//
//            //  Cap phat tat ca nguoi dung khong can dang nhap de dung
//            authorize.requestMatchers(HttpMethod.GET,"/api/todos").permitAll();
//
//            authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
//            authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
//
//            authorize.anyRequest().authenticated();
//        }).httpBasic(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
//
//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        UserDetails manager = User.builder().username("manager").password(passwordEncoder().encode("manager")).roles("MANAGER").build();
//    return new InMemoryUserDetailsManager(user,admin,manager);
//    }
//}
