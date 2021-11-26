package com.salesianostriana.dam.RealEstate_v2.security;

import com.salesianostriana.dam.RealEstate_v2.security.jwt.JwtAccessDeniedHandler;
import com.salesianostriana.dam.RealEstate_v2.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthorizationFilter filter;

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").anonymous()
                .antMatchers(HttpMethod.POST, "/user/auth/register/gestor").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/auth/register/propietario").anonymous()
                .antMatchers(HttpMethod.POST, "/user/auth/register/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/propietario").authenticated()
                .antMatchers(HttpMethod.GET, "/propietario/**").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/propietario/{id}").hasAnyRole("ADMIN", "PROPIETARIO")

                .antMatchers(HttpMethod.POST, "/vivienda/").authenticated()
                .antMatchers(HttpMethod.GET, "/vivienda/**").authenticated()
                .antMatchers(HttpMethod.POST, "/vivienda/").authenticated()
                .antMatchers(HttpMethod.PUT, "/vivienda/{id}").hasAnyRole("ADMIN" , "PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}").hasAnyRole("ADMIN" , "PROPIETARIO")
                .antMatchers(HttpMethod.POST, "/vivienda/{id}/inmobiliaria/{id2}").hasAnyRole("ADMIN" , "PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}/inmobiliaria/{id2}").hasAnyRole("ADMIN" , "PROPIETARIO", "GESTOR")

                .antMatchers(HttpMethod.GET, "/inmobiliaria").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/**").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/gestor/{id}").hasAnyRole("ADMIN" , "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}/gestor").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}/meinteresa").hasAnyRole("ADMIN" , "PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/interesado").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interesado/{id}").hasAnyRole("ADMIN" , "PROPIETARIO")


                .antMatchers(HttpMethod.GET, "/me").authenticated()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }


}
