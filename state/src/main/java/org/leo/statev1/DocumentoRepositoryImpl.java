package org.leo.statev1;

public class DocumentoRepositoryImpl implements DocumentoRepository {

	private DocumentoDAO documentoDAO = new DocumentoDAO();
	
	@Override
	public void emitir(Documento documento) {
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.inserir(documento);
	}

	@Override
	public void alterar(Documento documento) {
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.alterar(documento);
	}

	@Override
	public void cancelar(Documento documento) {
		documento.setEstado(EstadoDocumento.CANCELADO);
		documentoDAO.alterar(documento);
	}
	
	@Override
	public void consumir(Documento documento) {
		documento.setEstado(EstadoDocumento.CONSUMIDO);
		documentoDAO.alterar(documento);
	}
	
	@Override
	public Documento consultar(String numeroDocumento) {
		return documentoDAO.consultar(numeroDocumento);
	}

}
