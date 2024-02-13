package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
@Service
@Setter
@Getter
public class JwtUtilities {

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
    private String token;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public String createToken(Map<String, Object> claims, UserDetails userDetails) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("role", userDetails.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60))
                .signWith(SignatureAlgorithm.HS256, getSignKey()).compact();
    }

    public String extractUserRoleFromToken() {
        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .parseClaimsJws(getToken())
                .getBody();
        return (String) claims.get("role");
    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

//@Slf4j
////@Service
//@Component
//public class JwtUtilities {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long jwtExpiration;
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String email = extractUsername(token);
//        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
////    public Boolean isTokenExpired(String token) {
////        return extractExpiration(token).isBefore(new Date());
////    }
//public Boolean isTokenExpired(String token) {
//    Date expirationDate = extractExpiration(token);
//    Date currentDate = new Date();
//    return expirationDate.before(currentDate);
//}
//
//    public String generateToken(String email, List<String> roles) {
//        Instant expirationTime = Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS);
//        Date expirationDate = Date.from(expirationTime);
//
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", roles)
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            log.info("Invalid JWT token.");
//            log.trace("Invalid JWT token trace: {}", e);
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//            log.trace("Expired JWT token trace: {}", e);
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
//            log.trace("Unsupported JWT token trace: {}", e);
//        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
//            log.trace("JWT token compact of handler are invalid trace: {}", e);
//        }
//        return false;
//    }
//
//    public String getToken(HttpServletRequest httpServletRequest) {
//        final String bearerToken = httpServletRequest.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}