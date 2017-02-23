package org.leo.statev2;

public class StateEmitido implements State {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();

	@Override
	public void tentarGerar(Documento documento) {
		throw new IllegalStateException();
		
	}

	@Override
	public void tentarEmitir(String numeroDocumento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarAlterar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void tentarCancelar(String numeroDocumento) {
		repository.cancelar(numeroDocumento);
	}

	@Override
	public void tentarConsumir(Documento documento) {
		repository.consumir(documento);
	}

	@Override
	public void tentarNotificarRoubo(String numeroDocumento) {
		repository.notificarRoubo(numeroDocumento);
		
	}

	@Override
	public void tentarRecuperarRoubo(String numeroDocumento) {
		throw new IllegalStateException();
	}

}
