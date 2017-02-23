package org.leo.statev2;

public class StateGerado implements State {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void tentarGerar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarEmitir(String numeroDocumento) {
		repository.emitir(numeroDocumento);
	}

	@Override
	public void tentarAlterar(Documento documento) {
		repository.alterar(documento);
	}

	@Override
	public void tentarCancelar(String numeroDocumento) {
		throw new IllegalStateException();

	}

	@Override
	public void tentarConsumir(Documento documento) {
		throw new IllegalStateException();

	}

	@Override
	public void tentarNotificarRoubo(String numeroDocumento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarRecuperarRoubo(String numeroDocumento) {
		throw new IllegalStateException();
	}

}
