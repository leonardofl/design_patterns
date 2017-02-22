package org.leo.statev2;

public interface DocumentoRepository {

	void gerar(Documento documento);
	
	void emitir(String numeroDocumento);

	void alterar(Documento documento);

	void cancelar(String numeroDocumento);
	
	void consumir(Documento documento);
	
	void notificarRoubo(String numeroDocumento);
	
	void recuperarRoubo(String numeroDocumento);
	
	Documento consultar(String numeroDocumento);

}
