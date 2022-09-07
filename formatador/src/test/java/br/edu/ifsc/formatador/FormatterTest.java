package br.edu.ifsc.formatador;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class FormatterTest extends Formatter{
	enum ArredondaEnum {CIMA, BAIXO}
	

	@Test
	public void AlinhaDireitaTeste() {
		String teste = alinharDireita("Aula", 20);
		assertEquals("                Aula", teste);
	}
	@Test
	public void AlinhaEsquerdaTeste() {
		String teste = alinharEsquerda("Aula", 20);
		assertEquals("Aula                ", teste);
	}
	@Test
	public void AlinhaCentroTeste() {
		String teste = centralizar("Aula", 20);
		assertEquals("        Aula        ", teste);
	}
	@Test
	public void LinhaTeste() {
		String teste = linha(20);
		assertEquals("====================", teste);
	}
	@Test
	public void DinheiroTeste() {
		String teste = formatarReal(1.1211);
		assertEquals("R$ 1,12", teste);
	}
	@Test
	public void ArredondaCimaTeste() {
		long teste = arredonda(2,Formatter.ArredondaEnum.CIMA);
		assertEquals(2,  teste);
	}
	@Test
	public void ArredondaBaixoTeste() {
		long teste = arredonda(1,Formatter.ArredondaEnum.BAIXO);
		assertEquals(1, teste);
	}

	@Test
	public void FormataTeste() {
		double teste = arredonda(1.1234,3);
		assertEquals(""+1.123, ""+teste);
	}
}
