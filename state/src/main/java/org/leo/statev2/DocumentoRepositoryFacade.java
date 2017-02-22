package org.leo.statev2;

public class DocumentoRepositoryFacade implements DocumentoRepository {

	private StateFactory stateFactory = new StateFactory();
	
	@Override
	public void gerar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).gerar(documento);
	}

	@Override
	public void emitir(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).emitir(numeroDocumento);
	}
	
	private DocumentoRepository dependendoDoEstadoDoDocumento(String numeroDocumento) {
		return stateFactory.getStateForDocumento(numeroDocumento);
	}
	
	@Override
	public void alterar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).alterar(documento);
	}

	@Override
	public void cancelar(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).cancelar(numeroDocumento);
	}

	@Override
	public void consumir(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).consumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return dependendoDoEstadoDoDocumento(numeroDocumento).consultar(numeroDocumento);
	}

	@Override
	public void notificarRoubo(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).notificarRoubo(numeroDocumento);
	}

	@Override
	public void recuperarRoubo(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).recuperarRoubo(numeroDocumento);
	}

}
