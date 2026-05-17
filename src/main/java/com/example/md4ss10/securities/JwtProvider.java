package com.example.md4ss10.securities;

import com.example.md4ss10.models.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final String secret = "nguyenthuy92";
    private final long tokenValiditySeconds = 3600000;
    private final long tokenExpirationSeconds = 86400000;

    public String generateToken(User user){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenValiditySeconds);
        Claims claims =  Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getRole().toString());
        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public boolean validateToken(String token){
        return !Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().isEmpty();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }
}
