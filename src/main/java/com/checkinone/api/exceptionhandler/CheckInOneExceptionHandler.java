package com.checkinone.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.checkinone.service.exception.NegocioException;


@ControllerAdvice
public class CheckInOneExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({ NegocioException.class } )
	public ResponseEntity<Object> handleNegocioException(NegocioException negocioException, WebRequest request) {
		Erro erro = new Erro(negocioException.getMensagens().toArray(new String[0]));
		return handleExceptionInternal(negocioException, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		String mensagemInvalida = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, new Erro(mensagemInvalida), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String mensagemOperacaoNaoPermitida = messageSource.getMessage("mensagem.operacao-nao-permitida-violacao-integridade", null, LocaleContextHolder.getLocale());
		String erro = ExceptionUtils.getRootCauseMessage(ex);
		String[] detalhes = erro.split("Detalhe:");
		erro = detalhes != null &&  detalhes.length > 0 ? detalhes[detalhes.length-1] : erro;
		
		return handleExceptionInternal(ex, new Erro(mensagemOperacaoNaoPermitida, erro), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return handleExceptionInternal(ex, criarErro(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private Erro criarErro(BindingResult bindingResult) {
		Erro erro = new Erro();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			erro.adicionarMensagem(fieldError.getDefaultMessage());
		}
			
		return erro;
	}

	public static class Erro {

		private List<String> mensagens;
		
		public Erro() {
			this.mensagens = new ArrayList<>();
		}
		
		public Erro(String... mensagens) {
			this.mensagens = List.of(mensagens);
		}
		
		public void adicionarMensagem(String mensagem) {
			this.mensagens.add(mensagem);
		}

		public List<String> getMensagens() {
			return mensagens;
		}

		public void setMensagens(List<String> mensagens) {
			this.mensagens = mensagens;
		}
	}
}
