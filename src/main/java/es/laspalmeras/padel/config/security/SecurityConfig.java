package es.laspalmeras.padel.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilita CSRF si usas APIs REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll() // Permitir acceso público a ciertas rutas
                .anyRequest().authenticated() // Requerir autenticación para todo lo demás
            )
            .httpBasic(); // Usa autenticación básica (puedes cambiar a JWT o sesiones si lo prefieres)
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usar un encoder para las contraseñas
    }
}