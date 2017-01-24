package org.leo.ifv2;

import java.util.HashMap;
import java.util.Map;

public class DocumentoDAO {

	private static Map<String, Documento> documentos = new HashMap<>();
	
	public void inserir(Documento documento) {
		documentos.put(documento.getNumero(), documento);
	}

	public void alterar(Documento documento) {
		documentos.put(documento.getNumero(), documento);
	}

	public void remover(Documento documento) {
		documentos.remove(documento.getNumero());
	}
	
	public Documento consultar(String numeroDocumento) {
		return documentos.get(numeroDocumento);
	}

}
