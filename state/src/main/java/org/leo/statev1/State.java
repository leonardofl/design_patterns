package org.leo.statev1;

public interface State {

	void tentarEmitir(Documento documento);

	void tentarAlterar(Documento documento);

	void tentarCancelar(String numeroDocumento);
	
	void tentarConsumir(Documento documento);

}
