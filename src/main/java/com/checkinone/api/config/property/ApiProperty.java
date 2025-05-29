package com.checkinone.api.config.property;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("checkinone")
public class ApiProperty {

	private String originPermitida = "http://localhost:8180";
	private final Seguranca seguranca = new Seguranca();

	public String getOriginPermitida() {
		return originPermitida;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca {

		private List<String> urlsRedirectPermitidas;
		private String authServerUrl;
		public List<String> getUrlsRedirectPermitidas() {
			return urlsRedirectPermitidas;
		}
		public void setUrlsRedirectPermitidas(List<String> urlsRedirectPermitidas) {
			this.urlsRedirectPermitidas = urlsRedirectPermitidas;
		}
		public String getAuthServerUrl() {
			return authServerUrl;
		}
		public void setAuthServerUrl(String authServerUrl) {
			this.authServerUrl = authServerUrl;
		}
	}
}
