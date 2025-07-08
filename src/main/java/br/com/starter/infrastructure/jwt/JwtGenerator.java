package br.com.starter.infrastructure.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.starter.domain.user.CustomUserDetails;
import br.com.starter.domain.user.UserStatus;
import br.com.starter.infrastructure.exceptions.FrontDisplayableException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {

    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    static final long EXPIRATION_TIME = (1000 * 60 * 60 * 10); // 10 horas

    public String generateToken(Authentication authentication) {
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        var user = userDetails.getUser();

        
        var privileges = user.getPrivileges().stream()
                .map(privilege -> privilege.getName())
                .toArray(String[]::new);
        
        if (user.getStatus().equals(UserStatus.INACTIVE)) {
            throw new FrontDisplayableException(HttpStatus.FORBIDDEN, "Usuário inativo");
        }

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(user.getAuth().getUsername())
                .addClaims(
                    Map.of(
                        "profileId", user.getProfile().getId(),
                        "authorities", privileges,
                        "role", user.getRole().getName(),
                        "name", user.getProfile() == null
                                ? "Usuário Indefinido"
                                : user.getProfile().getName()
                    )
                )
                .setIssuedAt(new Date(System.currentTimeMillis())) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expira em 10 horas
                .signWith(key)
                .compact();
    }
}

