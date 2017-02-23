package org.leo.statev2;

public class StateConsumido implements State {

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
