package antho.demo_jwt.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import antho.demo_jwt.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String SECRET_KEY ="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    // public String getToken(UserDetails user) {
    //     return getToken(new HashMap<>(),user);    
    // }

    public String getToken(UserDetails user) {
        // Crear un mapa para los claims adicionales
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", ((User) user).getRole().name());
        return getToken(extraClaims,user);    
    }
        
    private String getToken(Map<String,Object> extraClaims, UserDetails user) { 
        // TODO Auto-generated method stub        
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getKey() {
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username =  getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));

    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Claims getAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token) // Cambiado de parseClaimsJwt a parseClaimsJws
        .getBody();


    }

    public <T> T  getClaim(String token, Function<Claims,T> ClaimsResolver){
        
        final Claims claims = getAllClaims(token);
        return ClaimsResolver.apply(claims);
    }

    private Date getExpiration(String token){

        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }


}
