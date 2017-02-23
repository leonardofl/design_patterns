package org.leo.statev2;

public class StateFactory {

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	public State getStateForDocumento(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento == null) {
			return new StateNovo();
		}
		if (documento.getEstado() == null) {
			throw new IllegalStateException("Documento " + numeroDocumento + " corrompido.");
		}
		switch (documento.getEstado()) {
		case GERADO:
			return new StateGerado();
		case EMITIDO:
			return new StateEmitido();
		case CANCELADO:
			return new StateCancelado();
		case CONSUMIDO:
			return new StateConsumido();
		case ROUBADO:
			return new StateRoubado();
		default:
			throw new IllegalArgumentException("Estado " + documento.getEstado() + " desconhecido por StateFcatory.");
		}
	}

}
