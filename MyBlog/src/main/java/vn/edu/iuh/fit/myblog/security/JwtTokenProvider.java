package vn.edu.iuh.fit.myblog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.UnsupportedKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.myblog.exception.BlogApiException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        System.out.println(jwtExpirationDate);
        Date expiredate = new Date(currentDate.getTime() + Long.parseLong(jwtExpirationDate));
        String token = Jwts.builder().subject(username).issuedAt(new Date()).expiration(expiredate).signWith(key()).compact();
        System.out.println(token);
        return token;

    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //    get username form Jwt token
    public String getUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        return username;
    }

    //    Valid JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(token);
            return true;

        } catch (MalformedJwtException e) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch (UnsupportedKeyException e) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims String is empty");
        }
    }

}
