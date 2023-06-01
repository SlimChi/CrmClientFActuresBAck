package clientcrm.frcs.config;

import clientcrm.frcs.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final int KEY_SIZE = 256;
    private static final int KEY_BYTE_SIZE = KEY_SIZE / 8;
    private static final String SECRET_KEY = SecretKeyGenerator.generateSecretKey();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails);
    }

    public String generateToken(Map<String,Object> claims, UserDetails userDetails){
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            String scope = user.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));

            return Jwts.builder().setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .claim("authorities", user.getAuthorities())
                    .claim("id", user.getEmail())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(200)))
                    .signWith(SignatureAlgorithm.HS256, getSignInKey()).compact();
        } else {
            throw new IllegalArgumentException("UserDetails object is not an instance of User class");
        }

    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public class SecretKeyGenerator {
        public static String generateSecretKey() {
            try {
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(256);
                SecretKey secretKey = keyGen.generateKey();
                byte[] encoded = secretKey.getEncoded();
                return Base64.getEncoder().encodeToString(encoded);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate secret key", e);
            }
        }
    }
}
