package org.leo.statev2;

public class StateEmitido implements DocumentoRepository {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();

	@Override
	public void gerar(Documento documento) {
		throw new IllegalStateException();
		
	}

	@Override
	public void emitir(String numeroDocumento) {
		throw new IllegalStateException();
	}

	@Override
	public void alterar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void cancelar(String numeroDocumento) {
		repository.cancelar(numeroDocumento);
	}

	@Override
	public void consumir(Documento documento) {
		repository.consumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return repository.consultar(numeroDocumento);
	}


	@Override
	public void notificarRoubo(String numeroDocumento) {
		repository.notificarRoubo(numeroDocumento);
		
	}

	@Override
	public void recuperarRoubo(String numeroDocumento) {
		throw new IllegalStateException();
	}

}
