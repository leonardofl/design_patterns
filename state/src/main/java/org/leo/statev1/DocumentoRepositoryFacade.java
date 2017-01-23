package org.leo.statev1;

public class DocumentoRepositoryFacade implements DocumentoRepository {

	private StateFactory stateFactory = new StateFactory();
	
	@Override
	public void emitir(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).emitir(documento);
	}

	private DocumentoRepository dependendoDoEstadoDoDocumento(String numeroDocumento) {
		return stateFactory.getStateForDocumento(numeroDocumento);
	}
	
	@Override
	public void alterar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).alterar(documento);
	}

	@Override
	public void cancelar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).cancelar(documento);
	}

	@Override
	public void consumir(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).consumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return dependendoDoEstadoDoDocumento(numeroDocumento).consultar(numeroDocumento);
	}

}
