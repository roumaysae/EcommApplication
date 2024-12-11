package com.roumaysae.inventoryservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(h -> h.frameOptions(f -> f.disable()))
                .csrf(csrf -> csrf.disable())
                //  desactiver le csrf pour simplifier les requetes POST depuis le client Angular
                //  (qui ne supporte pas le csrf), j'ai une communication entre deux serveurs stateless
                //  (Angular et Spring) donc pas besoin de csrf, il m'a donne des problemes de 403 forbidden
                //  lors de l'envoi des requetes POST depuis le client Angular vers le serveur Spring cela veut dire que le csrf est activé par defaut dans Spring Security et il faut le desactiver car le csrf est une protection contre les attaques CSRF (Cross-Site Request Forgery) qui sont des attaques qui permettent a un attaquant de forcer un utilisateur a executer des actions qu'il n'a pas autorisees (par exemple, changer son mot de passe, supprimer un compte, etc.) en utilisant des requetes HTTP non autorisees.
                .authorizeHttpRequests(ar->ar.requestMatchers("/apis/**","/h2-console/**").permitAll())  //  les requetes qui commencent par /apis/** sont permises sans authentification
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())         //  les autres requetes necessitent une authentification
                .build();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration  =   new CorsConfiguration() ;
    configuration.setAllowedOrigins(Arrays.asList("*") ); ;
    configuration.setAllowedMethods(Arrays.asList ("*" ) ); ;
    configuration.setAllowedHeaders(Arrays.asList ( "*") ); ;
    configuration.setExposedHeaders(Arrays.asList ("*") ) ;

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source. registerCorsConfiguration ( "/**" , configuration) ;
    return source;
}
}

