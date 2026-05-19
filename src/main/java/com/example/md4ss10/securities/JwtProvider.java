package com.example.md4ss10.securities;

import com.example.md4ss10.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secret = "nguyenthuy92";

    private final long tokenValiditySeconds = 3600000;

    public String generateToken(User user){

        Date now = new Date();

        Date expiryDate =
                new Date(now.getTime() + tokenValiditySeconds);

        Claims claims =
                Jwts.claims()
                        .setSubject(user.getUsername());

        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token){

        try {

            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e){

            return false;
        }
    }

    public String getUsernameFromToken(String token){

        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
