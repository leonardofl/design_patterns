package org.leo.ifv2;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class DocumentoRepositoryTest {

	static AtomicInteger counter = new AtomicInteger();
	
	DocumentoRepository repository = new DocumentoRepository();

	@Test
	public void shouldGerarDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.GERADO, documentoConsultado.getEstado());
	}

	@Test
	public void shouldEmitirDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.EMITIDO, documentoConsultado.getEstado());
	}

	private Documento novoDocumento() {
		Documento documento = new Documento();
		documento.setNumero(Integer.toString(counter.getAndIncrement()));
		documento.setTitular("Fulano");
		documento.setDate(hoje());
		return documento;
	}

	private Date hoje() {
		return new Date();
	}
	
	@Test
	public void shouldAlterarDocumentoGerado() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		documento.setTitular("Novo titular");
		repository.alterar(documento);
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.GERADO, documentoConsultado.getEstado());
	}

	@Test
	public void shouldCancelarDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.cancelar(documento.getNumero());
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.CANCELADO, documentoConsultado.getEstado());
	}	

	@Test
	public void shouldConsumirDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.consumir(documento);
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.CONSUMIDO, documentoConsultado.getEstado());
	}	
	
	@Test
	public void shouldNotificarRouboDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.notificarRoubo(documento.getNumero());
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.ROUBADO, documentoConsultado.getEstado());
	}

	@Test
	public void shouldRecuperarRouboDocumento() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.notificarRoubo(documento.getNumero());
		repository.recuperarRoubo(documento.getNumero());
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.EMITIDO, documentoConsultado.getEstado());
	}
	
	@Test
	public void shouldCancelarDocumentoRoubado() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.notificarRoubo(documento.getNumero());
		repository.cancelar(documento.getNumero());
		Documento documentoConsultado = repository.consultar(documento.getNumero());
		assertEquals(documento, documentoConsultado);
		assertEquals(EstadoDocumento.CANCELADO, documentoConsultado.getEstado());
	}
	
	
	
	///////////// Testes abaixo validam que transições inválidas não acontecem

	@Test(expected=IllegalStateException.class)
	public void shouldNotAlterarDocumentoCancelado() {
		Documento documento = novoDocumento();
		repository.emitir(documento.getNumero());
		repository.cancelar(documento.getNumero());
		documento.setTitular("Novo titular");
		repository.alterar(documento);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldNotAlterarDocumentoConsumido() {
		Documento documento = novoDocumento();
		repository.emitir(documento.getNumero());
		repository.consumir(documento);
		documento.setTitular("Novo titular");
		repository.alterar(documento);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldNotCancelarDocumentoConsumido() {
		Documento documento = novoDocumento();
		repository.emitir(documento.getNumero());
		repository.consumir(documento);
		repository.cancelar(documento.getNumero());
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNotConsumirDocumentoCancelado() {
		Documento documento = novoDocumento();
		repository.emitir(documento.getNumero());
		repository.cancelar(documento.getNumero());
		repository.consumir(documento);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldNaoCancelarDocumentoNaoExistente() {
		repository.cancelar("Número inexistente");
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNaoAlterarDocumentoEmitido() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		documento.setTitular("Novo titular");
		repository.alterar(documento);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldNaoNotificarRouboDocumentoGerado() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.notificarRoubo(documento.getNumero());
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNaoNotificarRouboDocumentoConsumido() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.consumir(documento);
		repository.notificarRoubo(documento.getNumero());
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNaoNotificarRouboDocumentoCancelado() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.cancelar(documento.getNumero());
		repository.notificarRoubo(documento.getNumero());
	}

	@Test(expected=IllegalStateException.class)
	public void shouldNaoRecuperarDocumentoNaoRoubado() {
		Documento documento = novoDocumento();
		repository.gerar(documento);
		repository.emitir(documento.getNumero());
		repository.recuperarRoubo(documento.getNumero());
	}

}
