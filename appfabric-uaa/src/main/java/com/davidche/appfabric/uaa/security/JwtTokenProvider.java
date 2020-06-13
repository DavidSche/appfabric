package com.davidche.appfabric.uaa.security;

import com.davidche.appfabric.uaa.exception.InvalidTokenRequestException;
import com.davidche.appfabric.uaa.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

//    private static final Logger logger = Logger.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    //  令牌生效时间
    @Value("${app.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${app.jwt.claims.refresh.name}")
    private String jwtClaimRefreshName;

    /**
     * 从用户账号数据声明生成令牌
     * Generates a token from a principal object. Embed the refresh token in the jwt
     * so that a new jwt can be created
     */
    public String generateToken(CustomUserDetails customUserDetails) {
        Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(Long.toString(customUserDetails.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * 从用户账号ID生成令牌
     * Generates a token from a principal object. Embed the refresh token in the jwt
     * so that a new jwt can be created
     */
    public String generateTokenFromUserId(Long userId) {
        Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    /**
     * Returns the user id encapsulated within the token
     * 从令牌获取用户ID信息
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * 验证令牌信息是否有效
     * Validates if a token has the correct unmalformed signature and is not expired or unsupported.
     */
    public boolean validateToken(String authToken) {
        boolean isTokenVerified = false;
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            isTokenVerified = true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw new InvalidTokenRequestException("JWT", authToken, "Incorrect signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new InvalidTokenRequestException("JWT", authToken, "Malformed jwt token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new InvalidTokenRequestException("JWT", authToken, "Token expired. Refresh required");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new InvalidTokenRequestException("JWT", authToken, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new InvalidTokenRequestException("JWT", authToken, "Illegal argument token");
        } finally {
            return isTokenVerified;
        }
    }

    /**
     * 令牌过期信息
     * <p>
     * Return the jwt expiration for the client so that they can execute
     * the refresh token logic appropriately
     */
    public Long getExpiryDuration() {
        return jwtExpirationInMs;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationInMs);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        CustomUserDetails user = (CustomUserDetails) userDetails;
        Long userId = getUserIdFromJWT(token);
        return (userId.equals(user.getId()) && !isTokenExpired(token));
    }

}
