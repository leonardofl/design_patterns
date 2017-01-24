package org.leo.statev1;

public interface DocumentoRepository {

	void emitir(Documento documento);

	void alterar(Documento documento);

	void cancelar(String numeroDocumento);
	
	void consumir(Documento documento);
	
	Documento consultar(String numeroDocumento);

}
