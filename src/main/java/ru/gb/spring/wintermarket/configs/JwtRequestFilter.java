package ru.gb.spring.wintermarket.configs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.gb.spring.wintermarket.utils.JwtTokenUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

//если юзер стучится в защищенную область этот фильтр включается в работу
@Component
@RequiredArgsConstructor
@Slf4j
//постоянно проверяет наличие токена при каждом запросе
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Запрос токена из заголовка
        String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwt);//и парсинг и валидация
            }catch (ExpiredJwtException | MalformedJwtException e){
                log.warn("The token is expired or malformed!");
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //В обход security кладём в контекст!
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username,
                            null,
                            jwtTokenUtil.getRoles(jwt)
                                        .stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList()));
            //Кладём сами в контекст, дальше фильтра считают пользователя авторизованным
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request,response);
    }
}
