package org.leo.statev2;

public class DocumentoRepositoryImpl implements DocumentoRepository {

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	@Override
	public void gerar(Documento documento) {
		documento.setEstado(EstadoDocumento.GERADO);
		documentoDAO.inserir(documento);
	}

	@Override
	public void emitir(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.alterar(documento);
	}

	@Override
	public void alterar(Documento documento) {
		documento.setEstado(EstadoDocumento.GERADO);
		documentoDAO.alterar(documento);
	}

	@Override
	public void cancelar(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
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


	@Override
	public void notificarRoubo(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		documento.setEstado(EstadoDocumento.ROUBADO);
		documentoDAO.alterar(documento);
	}

	@Override
	public void recuperarRoubo(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		documento.setEstado(EstadoDocumento.EMITIDO);
		documentoDAO.alterar(documento);
	}

}
