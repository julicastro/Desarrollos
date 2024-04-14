package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.persistence.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt,expiration-in-minuts}")
    private Long EXPIRATION_IN_MINUTS;

    @Value("${security.jwt,secrete-key}")
    private String SECRET_KEY;

    /*
    // version 0.11.X. aunque todavía funciona...
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        // UserDetails -> interface implementa en la entidad usuario
        // jjwt library.
        // usamos builder para contruir el jwt como tal
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTS * 60 * 1000) + issuedAt.getTime()); // EXPIRATION_IN_MINUTS convertido a mls
        String jwt = Jwts.builder()
                // seteamos los claims (propierdades del payload)
                .setClaims(extraClaims)
                .setSubject(user.getUsername()) //propiertario
                .setIssuedAt(issuedAt) // mls
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256) // firmar con...
                .compact(); // devuelve un stirng q equivale a nuestro JWT
        return jwt;
    }
    */

    // version 0.12.3
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        // UserDetails -> interface implementa en la entidad usuario
        // jjwt library.
        // usamos builder para contruir el jwt como tal
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((EXPIRATION_IN_MINUTS * 60 * 1000) + issuedAt.getTime()); // EXPIRATION_IN_MINUTS convertido a mls
        String jwt = Jwts.builder()
                // header
                .header()
                    .type("JWT")
                    .and()
                // payload
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)
                // firma
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    /*
    // version 0.11.X. aunque todavía funciona...
    private Key generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        // la clave viene como base64. en este caso del .properties. x eso la decodifica a bytes equivalentes al string común
        return Keys.hmacShaKeyFor(passwordDecoded); // arreglo de bytes q equivale a mi clave en string
    }
    */

    // version 0.12.3
    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        // la clave viene como base64. en este caso del .properties. x eso la decodifica a bytes equivalentes al string común
        return Keys.hmacShaKeyFor(passwordDecoded); // arreglo de bytes q equivale a mi clave en string
    }

    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    /*
    // version 0.11.X. aunque todavía funciona...
    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody(); // parseClaimsJws se usa cuando el jwt está firmado
        // ClaimJws es un JWT q ha sido firmado. es decir tiene paylod q ha sido criptograficamente firmado.
    }
    */

    // version 0.12.3
    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(jwt).getPayload(); // parseClaimsJws se usa cuando el jwt está firmado
        // ClaimJws es un JWT q ha sido firmado. es decir tiene paylod q ha sido criptograficamente firmado.
    }


}
