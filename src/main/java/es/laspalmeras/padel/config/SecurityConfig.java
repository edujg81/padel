package es.laspalmeras.padel.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {     
        http
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/public/**").permitAll()
                                .anyRequest().authenticated()
                )
//                .formLogin(form -> form
//                		.loginPage("/login").permitAll()
//                )
//                .logout(logout -> logout.permitAll());
                .formLogin(Customizer.withDefaults());
		return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar un encoder para las contraseñas
    }
}