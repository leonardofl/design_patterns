package org.leo.statev1;

public class StateNovo implements DocumentoRepository {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void emitir(Documento documento) {
		repository.emitir(documento);
	}

	@Override
	public void alterar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void cancelar(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void consumir(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		throw new UnsupportedOperationException();
	}

}
