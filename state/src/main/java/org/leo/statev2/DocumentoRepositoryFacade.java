package org.leo.statev2;

public class DocumentoRepositoryFacade implements DocumentoRepository {

	private StateFactory stateFactory = new StateFactory();
	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void gerar(Documento documento) {
		dependendoDoEstadoDoDocumento(documento.getNumero()).tentarGerar(documento);
	}

	@Override
	public void emitir(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).tentarEmitir(numeroDocumento);
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

	@Override
	public void notificarRoubo(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).tentarNotificarRoubo(numeroDocumento);
	}

	@Override
	public void recuperarRoubo(String numeroDocumento) {
		dependendoDoEstadoDoDocumento(numeroDocumento).tentarRecuperarRoubo(numeroDocumento);
	}

}
