package be.technifutur.technisandwich.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtProvider {
    private final UserDetailsService userDetailsService;
    private final int expireTime = 24*60*60*1000;
    private final String secret = "ex:b3nj4m1ch35";

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(Authentication auth) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + expireTime);

        try {
            return JWT.create()
                    .withSubject( auth.getName() )
                    .withExpiresAt( expiresAt )
                    .withIssuedAt( issuedAt )
                    .sign( Algorithm.HMAC512(secret) );
        }
        catch ( UnsupportedEncodingException ex ){
            throw new RuntimeException("internal error", ex);
        }
    }

    public boolean validateToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secret))
                    .acceptExpiresAt(expireTime)
                    .build();

            DecodedJWT decodedJWT = verifier.verify( token );

            return true;
        }
        catch (JWTVerificationException ex){
            return false;
        }
        catch (UnsupportedEncodingException ex){
            throw new RuntimeException("internal error", ex);
        }
    }

    public Authentication generateAuth(String token){
        DecodedJWT jwt = JWT.decode(token);
        String mail = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(mail);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }

}