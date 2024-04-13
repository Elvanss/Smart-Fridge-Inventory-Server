package com.smart.inventory.Config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Security.CustomBasicAuthenticationEntryPoint;
import com.smart.inventory.Security.CustomBearerTokenAccessDeniedHandler;
import com.smart.inventory.Security.CustomBearerTokenAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfiguration {

    private final RSAPublicKey publicKey;

    private final RSAPrivateKey privateKey;

    @Value("/api/v1")
    private String baseUrl;

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    private final CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint;

    private final CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler;

    public SecurityConfiguration(CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint,
                                 CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint,
                                 CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler) throws NoSuchAlgorithmException {

        this.customBasicAuthenticationEntryPoint = customBasicAuthenticationEntryPoint;
        this.customBearerTokenAuthenticationEntryPoint = customBearerTokenAuthenticationEntryPoint;
        this.customBearerTokenAccessDeniedHandler = customBearerTokenAccessDeniedHandler;

        // Generate a public/private key pair.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // The generated key will have a size of 2048 bits.
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity https) throws Exception {
        https
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET, this.baseUrl + "/profiles/**").permitAll() // Allow everyone to access the endpoint.
                                .requestMatchers(HttpMethod.GET, this.baseUrl + "/users/**").hasRole(RoleList.ADMIN.name()) // Protect the endpoint.
                                .requestMatchers(HttpMethod.POST, this.baseUrl + "/users").hasRole(RoleList.ADMIN.name()) // Protect the endpoint.
                                .requestMatchers(HttpMethod.POST, this.baseUrl + "/users/register").permitAll() // Protect the endpoint.
                                .requestMatchers(HttpMethod.PUT, this.baseUrl + "/users/**").hasRole(RoleList.ADMIN.name()) // Protect the endpoint.
                                .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/users/**").hasRole(RoleList.ADMIN.name()) // Protect the endpoint.
                                //Alow swaggerUI with path http://localhost:8080/swagger-ui/index.html
                                .requestMatchers("/swagger-ui/**","/v3/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/actuator/**").permitAll()
                                .anyRequest().authenticated()

                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(httpBasic ->
                        httpBasic.authenticationEntryPoint(this.customBasicAuthenticationEntryPoint)
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt ->
                                        jwt.decoder(this.jwtDecoder())
                                )
                                .authenticationEntryPoint(this.customBearerTokenAuthenticationEntryPoint)
                                .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler)
                );
        return https.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        /*
        Let’s say that that your authorization server communicates authorities in a custom claim called "authorities".
        In that case, you can configure the claim that JwtAuthenticationConverter should inspect, like so:
         */
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        /*
        You can also configure the authority prefix to be different as well. The default one is "SCOPE_".
        In this project, you need to change it to empty, that is, no prefix!
         */
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
