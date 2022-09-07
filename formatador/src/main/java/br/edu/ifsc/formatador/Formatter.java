package br.edu.ifsc.formatador;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class Formatter {
	enum ArredondaEnum {CIMA, BAIXO}
	
	public static String alinharDireita(String st, int colunas) {
		return StringUtils.leftPad(st, colunas);
	}
	
	public static String alinharEsquerda(String st, int colunas) {	
		return StringUtils.rightPad(st, colunas);
	}
	
	public static String centralizar(String st, int colunas) {
		return StringUtils.center(st, colunas);
	}
	public static String linha(int colunas) {
		return StringUtils.repeat("=", colunas);
	}
	public static String formatarReal(double valor) {
		String texto = StringUtils.replace("R$ "+valor, ".", ",");
		int indice = texto.indexOf(",");
		return StringUtils.substring(texto, 0, indice+3);
	}
	
	public static long arredonda(double valor, ArredondaEnum arred) {
		long arredondado = 0; 
		if (arred.equals(ArredondaEnum.CIMA)){
			arredondado = (long) Math.ceil(valor);
		} else {
			arredondado = (long) Math.floor(valor);
		}
		return arredondado;
	}
	public static double arredonda(double valor, int decimais) {

		int indice = (""+valor).indexOf(".");
		return Double.parseDouble(StringUtils.substring(""+valor, 0, indice+decimais+1));
	}
	
	public static void main(String[] args){
		System.out.println(alinharDireita("Aula",20));
		System.out.println(alinharEsquerda("Aula",20));
		System.out.println(centralizar("Aula",20));
		System.out.println(linha(20));
		System.out.println(formatarReal(1.1211));
		System.out.println(arredonda(1.1,ArredondaEnum.CIMA));
		System.out.println(arredonda(1.1,ArredondaEnum.BAIXO));
		System.out.println(arredonda(1.1234, 3));
		
	}
	
/*	
	alinharDireita("Aula",20);
	alinharEsquerda("Aula",20);
	centralizar("Aula",20);
	linha(20);
	formatarReal(1.1211)
	arredonda(1.1,CIMA)
	arredonda(1.1,BAIXO)
	formata(1.1234, 3)
	Resultados
	                Aula
	
	Aula
	        Aula
	
	====================
	
	R$ 1,12
	2
	1
	1.123
*/
}
