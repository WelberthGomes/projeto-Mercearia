package br.com.mercearia.config.validacao.dto;

public class ErroDeFormularioDto {
	private String erro;
	private String campo;
	
	public ErroDeFormularioDto(String erro, String campo) {
		super();
		this.erro = erro;
		this.campo = campo;
	}

	public String getErro() {
		return erro;
	}

	public String getCampo() {
		return campo;
	}
	
	
}
