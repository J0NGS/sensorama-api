package br.com.starter.infrastructure.config.security;
    import br.com.starter.domain.role.RoleType;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    import br.com.starter.infrastructure.jwt.JwtAuthenticationFilter;

    @Configuration
    public class SecurityFilter {
        // Rotas padrão
        public static final String BASE_URL = "/sensorama/api";

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .exceptionHandling(exceptionHandling -> exceptionHandling.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(CorsConfig.corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/error").anonymous()
                    // -------------------------------------------- Users routes --------------------------------------------
                    .requestMatchers(BASE_URL + "/users/register").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(BASE_URL + "/users/{userId}/update-password").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(BASE_URL + "/users/{userId}/update-username").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.PUT ,BASE_URL + "/users/{userId}").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(BASE_URL + "/users/{userId}/privileges/{privilegeId}/remove").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(BASE_URL + "/users/{userId}/privileges/{privilegeId}/add").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(BASE_URL + "/users/{userId}/update-role/{roleId}").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.DELETE, BASE_URL + "users/{userId}").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.GET, BASE_URL + "users/{userId}").hasRole(RoleType.ROLE_USER.getName())
                    // -------------------------------------------- role routes --------------------------------------------
                    .requestMatchers(BASE_URL + "/roles/{id}/privileges").hasRole(RoleType.ROLE_USER.getName())
                    .requestMatchers(HttpMethod.POST, BASE_URL + "/roles/{id}/privileges").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.DELETE, BASE_URL + "/roles/{id}/privileges").hasRole(RoleType.ROLE_ADMIN.getName())
                    // -------------------------------------------- privileges routes --------------------------------------------
                    .requestMatchers(HttpMethod.POST, BASE_URL + "/privileges").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.PATCH, BASE_URL + "/privileges/{id}/signature-revoked").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.DELETE, BASE_URL + "/privileges/{id}").hasRole(RoleType.ROLE_ADMIN.getName())
                    .requestMatchers(HttpMethod.GET, BASE_URL + "/privileges").hasRole(RoleType.ROLE_USER.getName())
                    .requestMatchers(BASE_URL + "/public/**").permitAll()
                    .anyRequest().denyAll())
                    .sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }
