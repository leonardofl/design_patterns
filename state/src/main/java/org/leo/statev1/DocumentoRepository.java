package org.leo.statev1;

public interface DocumentoRepository {

	void emitir(Documento documento);

	void alterar(Documento documento);

	void cancelar(Documento documento);
	
	void consumir(Documento documento);
	
	Documento consultar(String numeroDocumento);

}
