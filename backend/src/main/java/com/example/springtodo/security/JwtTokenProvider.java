package com.example.springtodo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    private final JwtConfigure jwtConfigure;

    private final Algorithm algorithm;

    private final long expiresAt;

    private final JWTVerifier verifier;

    public JwtTokenProvider(JwtConfigure jwtConfigure) {
        this.jwtConfigure = jwtConfigure;
        this.algorithm = Algorithm.HMAC256(this.jwtConfigure.getSecretKey());
        this.expiresAt = new Date().getTime() + jwtConfigure.getExpirySeconds() * 1000L;
        this.verifier = JWT.require(algorithm).withIssuer(jwtConfigure.getIssuer()).build();
    }

    public String createToken(Integer userId) {
        return JWT.create()
                .withIssuer(jwtConfigure.getIssuer())
                .withExpiresAt(new Date(expiresAt))
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    public String verifyToken(String token) {
        try{
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("userId").toString();
        } catch (JWTVerificationException exception) {
            throw new IllegalArgumentException("JWT Verify 예외 발생", exception);
        }
    }
}
