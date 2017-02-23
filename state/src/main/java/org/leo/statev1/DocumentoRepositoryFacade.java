package org.leo.statev1;

public class DocumentoRepositoryFacade implements DocumentoRepository {

	private StateFactory stateFactory = new StateFactory();
	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void emitir(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).tentarEmitir(documento);
	}

	private State dependendoDoEstadoDoDocumento(String numeroDocumento) {
		return stateFactory.getStateForDocumento(numeroDocumento);
	}
	
	@Override
	public void alterar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).tentarAlterar(documento);
	}

	@Override
	public void cancelar(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).tentarCancelar(numeroDocumento);
	}

	@Override
	public void consumir(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).tentarConsumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return repository.consultar(numeroDocumento);
	}

}
