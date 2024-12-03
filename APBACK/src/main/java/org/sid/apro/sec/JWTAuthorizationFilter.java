package org.sid.apro.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(JWTAuthorizationFilter.class.getName());


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }


        String jwtToken = request.getHeader(securityParams.JWTheaderName);
        logger.info("JWT Token: " + jwtToken);
        if (jwtToken == null || !jwtToken.startsWith(securityParams.HEADER_PREFIX)) {
            logger.warning("JWT Token is missing or does not start with the correct prefix");
            filterChain.doFilter(request, response);
            return;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(securityParams.SECRET)).build();
        String jwt = jwtToken.substring(securityParams.HEADER_PREFIX.length());
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

        logger.info("Username: " + username);
        logger.info("Roles: " + roles);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);
    }
}