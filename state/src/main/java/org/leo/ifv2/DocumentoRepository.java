package org.leo.ifv2;

public class DocumentoRepository {

	private DocumentoDAO documentoDAO = new DocumentoDAO();

	public void gerar(Documento documento) {
		if (documento.getEstado() == null) {
			documento.setEstado(EstadoDocumento.GERADO);
			documentoDAO.inserir(documento);
		} else {
			throw new IllegalStateException("Geração é apenas para novos documentos.");
		}
	}
	
	public void emitir(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento != null && documento.getEstado() == EstadoDocumento.GERADO) {
			documento.setEstado(EstadoDocumento.EMITIDO);
			documentoDAO.inserir(documento);
		} else {
			throw new IllegalStateException("Emissão é apenas para documentos gerados.");
		}
	}

	public void alterar(Documento documento) {
		if (documento.getEstado() == EstadoDocumento.GERADO) {
			documento.setEstado(EstadoDocumento.GERADO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Alteração é apenas para documentos gerados.");
		}
	}

	public void cancelar(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento != null && (documento.getEstado() == EstadoDocumento.EMITIDO || documento.getEstado() == EstadoDocumento.ROUBADO)) {
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

	public void notificarRoubo(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento.getEstado() == EstadoDocumento.EMITIDO) {
			documento.setEstado(EstadoDocumento.ROUBADO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Notificação de roubo é apenas para documentos emitidos.");
		}		
	}
	
	public void recuperarRoubo(String numeroDocumento) {
		Documento documento = documentoDAO.consultar(numeroDocumento);
		if (documento.getEstado() == EstadoDocumento.ROUBADO) {
			documento.setEstado(EstadoDocumento.EMITIDO);
			documentoDAO.alterar(documento);
		} else {
			throw new IllegalStateException("Recuperação de roubo é apenas para documentos roubados.");
		}		
	}
	
	public Documento consultar(String numeroDocumento) {
		return documentoDAO.consultar(numeroDocumento);
	}

}
