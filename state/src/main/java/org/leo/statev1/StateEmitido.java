package org.leo.statev1;

public class StateEmitido implements State {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void tentarEmitir(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarAlterar(Documento documento) {
		repository.alterar(documento);
	}

	@Override
	public void tentarCancelar(String numeroDocumento) {
		repository.cancelar(numeroDocumento);
	}

	@Override
	public void tentarConsumir(Documento documento) {
		repository.consumir(documento);
	}

}
