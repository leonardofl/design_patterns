package org.leo.v0;

public class DocumentoRepository {

	private DocumentoDAO documentoDAO = new DocumentoDAO();
	
	public void emitir(Documento documento) {
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.inserir(documento);
	}

	public void alterar(Documento documento) {
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.alterar(documento);
	}

	public void cancelar(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		documento.setEstado(EstadoDocumento.CANCELADO);
		documentoDAO.alterar(documento);
	}
	
	public void consumir(Documento documento) {
		documento.setEstado(EstadoDocumento.CONSUMIDO);
		documentoDAO.alterar(documento);
	}
	
	public Documento consultar(String numeroDocumento) {
		return documentoDAO.consultar(numeroDocumento);
	}

}
