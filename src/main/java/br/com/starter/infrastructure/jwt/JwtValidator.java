package br.com.starter.infrastructure.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtValidator {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public Claims validateTokenAndGetClaims(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "Token expirado");
        } catch (JwtException e) {
            throw new JwtException("Token inválido");
        }
    }

    public boolean isTokenValid(String token, String username) {
        Claims claims = validateTokenAndGetClaims(token);
        String tokenUsername = claims.getSubject();
        return (tokenUsername != null && tokenUsername.equals(username) && !isTokenExpired(claims));
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
