package com.checkinone.api.config;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.checkinone.api.config.property.ApiProperty;
import com.checkinone.api.security.UsuarioSistema;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ApiProperty apiProperty;
	
	@Bean
	RegisteredClientRepository registeredClientRepository() {
		RegisteredClient checkione = RegisteredClient
				.withId(UUID.randomUUID().toString())
				.clientId("checkinone")
				.clientSecret(passwordEncoder.encode("w3b-cl13nt-s3cr3t"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUris(uris -> uris.addAll(apiProperty.getSeguranca().getUrlsRedirectPermitidas()))
				.scope("read")
				.scope("write")
				.tokenSettings(TokenSettings.builder()
						.accessTokenTimeToLive(Duration.ofMinutes(30))
						.refreshTokenTimeToLive(Duration.ofDays(24))
						.build())
				.clientSettings(
						ClientSettings.builder()
							.build())
				.build();
		
		return new InMemoryRegisteredClientRepository(checkione);
	}
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authServerFilterChain(HttpSecurity http) throws Exception {
	    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
	    return http.formLogin(Customizer.withDefaults()).build();
	}

    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> jwtBuildCustomizer() {
        return (context) -> {
            UsernamePasswordAuthenticationToken authenticationToken = context.getPrincipal();
            UsuarioSistema usuarioSistema = (UsuarioSistema) authenticationToken.getPrincipal();

            Set<String> authorities = new HashSet<>();
            for (GrantedAuthority grantedAuthority : usuarioSistema.getAuthorities()) {
                authorities.add(grantedAuthority.getAuthority());
            }

            context.getClaims().claim("name", usuarioSistema.getUsuario().getNome());
            context.getClaims().claim("authorities", authorities);
        };
    }

    @Bean
    JWKSet jwkSet() throws JOSEException {
        RSAKey rsa = new RSAKeyGenerator(2048)
                .keyUse(KeyUse.SIGNATURE)
                .keyID(UUID.randomUUID().toString())
                .generate();

        return new JWKSet(rsa);
    }

    @Bean
    JWKSource<SecurityContext> jwkSource(JWKSet jwkSet) {
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    AuthorizationServerSettings providerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer(apiProperty.getSeguranca().getAuthServerUrl())
                .build();
    }
}
