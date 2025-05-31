package com.checkinone.api.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkinone.api.security.UsuarioSistema;
import com.checkinone.model.Usuario;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

	@GetMapping("/userinfo")
    public Map<String, Object> userInfo() {
    	Map<String, Object> claims = new HashMap<>();
    	if(SecurityContextHolder.getContext().getAuthentication() instanceof JwtAuthenticationToken) {
    		JwtAuthenticationToken token = (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
    		claims = token.getTokenAttributes();
    	} else if(SecurityContextHolder.getContext().getAuthentication() instanceof UsuarioSistema) {
    		UsuarioSistema usuarioSistema = (UsuarioSistema) SecurityContextHolder.getContext().getAuthentication();
    		Usuario usuario = usuarioSistema.getUsuario();
            
            claims.put("sub", usuarioSistema.getUsername());
            claims.put("name", usuario.getNome());
            claims.put("email", usuario.getEmail());
            claims.put("authorithies", usuario.getPermissoes());
    	}
    	
    	return claims;
    }
}
