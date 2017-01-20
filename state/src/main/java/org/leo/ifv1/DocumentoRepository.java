package org.leo.ifv1;

public class DocumentoRepository {

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	public void emitir(Documento documento) {
		if (documento.getEstado() == null) {
			documento.setEstado(EstadoDocumento.EMITIDO);
			documentoDAO.inserir(documento);
		} else {
			throw new IllegalStateException("Emissão é apenas para novos documentos.");
		}
	}

	public void alterar(Documento documento) {
		if (documento.getEstado() == EstadoDocumento.EMITIDO) {
			documento.setEstado(EstadoDocumento.EMITIDO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Alteração é apenas para documentos emitidos.");
		}
	}

	public void cancelar(Documento documento) {
		if (documento.getEstado() == EstadoDocumento.EMITIDO) {
			documento.setEstado(EstadoDocumento.CANCELADO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Cancelamento é apenas para documentos emitidos.");
		}
	}

	public void consumir(Documento documento) {
		if (documento.getEstado() == EstadoDocumento.EMITIDO) {
			documento.setEstado(EstadoDocumento.CONSUMIDO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Consumo é apenas para documentos emitidos.");
		}
	}

	public Documento consultar(String numeroDocumento) {
		return documentoDAO.consultar(numeroDocumento);
	}

}
