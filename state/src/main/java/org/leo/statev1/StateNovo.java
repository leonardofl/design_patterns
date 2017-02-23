package org.leo.statev1;

public class StateNovo implements State {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void tentarEmitir(Documento documento) {
		repository.emitir(documento);
	}

	@Override
	public void tentarAlterar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarCancelar(String numeroDocumento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarConsumir(Documento documento) {
		throw new IllegalStateException();
	}

}
