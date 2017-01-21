package org.leo.statev1;

public class StateFactory {

	DocumentoRepository getStateFor(Documento documento) {
		if (documento.getEstado() == null) {
			return new StateNovo();
		}
		switch (documento.getEstado()) {
		case EMITIDO:
			return new StateEmitido();
		case CANCELADO:
			return new StateCancelado();
		case CONSUMIDO:
			return new StateConsumido();
		default:
			throw new IllegalStateException("Estado " + documento.getEstado() + " desconhecido por StateFcatory.");
		}
	}

}
