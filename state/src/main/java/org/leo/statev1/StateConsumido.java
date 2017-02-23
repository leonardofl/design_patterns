package org.leo.statev1;

public class StateConsumido implements State {

	@Override
	public void tentarEmitir(Documento documento) {
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

}
