package com.example.md4ss10.securities;

import com.example.md4ss10.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET_KEY = "mySecretKey";

    @Value("${jwt.expiration}")
    private long EXPIRATION;
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
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return true;

        } catch (ExpiredJwtException e) {

            System.out.println("Token hết hạn");

        } catch (MalformedJwtException e) {

            System.out.println("Token sai định dạng");

        } catch (Exception e) {

            System.out.println("Token không hợp lệ");
        }

        return false;
    }

    public String getUsernameFromToken(String token){

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(Authentication authentication) {

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(
                        SignatureAlgorithm.HS512,
                        SECRET_KEY
                )
                .compact();
    }
}
