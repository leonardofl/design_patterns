package org.leo.statev2;

public interface State {

	void tentarGerar(Documento documento);
	
	void tentarEmitir(String numeroDocumento);

	void tentarAlterar(Documento documento);

	void tentarCancelar(String numeroDocumento);
	
	void tentarConsumir(Documento documento);
	
	void tentarNotificarRoubo(String numeroDocumento);
	
	void tentarRecuperarRoubo(String numeroDocumento);

}
