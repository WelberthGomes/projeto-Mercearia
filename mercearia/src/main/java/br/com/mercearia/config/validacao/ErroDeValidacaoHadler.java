package br.com.mercearia.config.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mercearia.config.validacao.dto.ErroDeFormularioDto;

@RestControllerAdvice
public class ErroDeValidacaoHadler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {

		List<ErroDeFormularioDto> listErroDeFormularioDto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.stream().forEach(e -> {
			ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto(
					messageSource.getMessage(e, LocaleContextHolder.getLocale()), e.getField());

			listErroDeFormularioDto.add(erroDeFormularioDto);
		});
		return listErroDeFormularioDto;
	}
}
