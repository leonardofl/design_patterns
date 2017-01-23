package org.leo.statev1;

public class StateEmitido implements DocumentoRepository {

	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void emitir(Documento documento) {
		throw new IllegalStateException();
	}

	@Override
	public void alterar(Documento documento) {
		repository.emitir(documento);
	}

	@Override
	public void cancelar(Documento documento) {
		repository.cancelar(documento);
	}

	@Override
	public void consumir(Documento documento) {
		repository.consumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return repository.consultar(numeroDocumento);
	}

}
