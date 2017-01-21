package org.leo.statev1;

public class DocumentoRepositoryFacade implements DocumentoRepository {

	private StateFactory stateFactory = new StateFactory();
	private DocumentoRepository repository = new DocumentoRepositoryImpl();
	
	@Override
	public void emitir(Documento documento) {
		dependendoDoEstadoDo(documento).emitir(documento);
	}

	private DocumentoRepository dependendoDoEstadoDo(Documento documento) {
		return stateFactory.getStateFor(documento);
	}
	
	@Override
	public void alterar(Documento documento) {
		dependendoDoEstadoDo(documento).alterar(documento);
	}

	@Override
	public void cancelar(Documento documento) {
		dependendoDoEstadoDo(documento).cancelar(documento);
	}

	@Override
	public void consumir(Documento documento) {
		dependendoDoEstadoDo(documento).consumir(documento);
	}

	@Override
	public Documento consultar(String numeroDocumento) {
		return repository.consultar(numeroDocumento);
	}

}
