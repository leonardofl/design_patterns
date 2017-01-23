package org.leo.statev1;

public class StateFactory {

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	public DocumentoRepository getStateForDocumento(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento == null) {
			return new StateNovo();
		}
		if (documento.getEstado() == null) {
			throw new IllegalStateException("Documento " + numeroDocumento + " corrompido.");
		}
		switch (documento.getEstado()) {
		case EMITIDO:
			return new StateEmitido();
		case CANCELADO:
			return new StateCancelado();
		case CONSUMIDO:
			return new StateConsumido();
		default:
			throw new IllegalArgumentException("Estado " + documento.getEstado() + " desconhecido por StateFcatory.");
		}
	}

}
