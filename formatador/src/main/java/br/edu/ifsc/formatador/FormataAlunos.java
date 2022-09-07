package br.edu.ifsc.formatador;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import br.edu.ifsc.formatador.Formatter.ArredondaEnum;

public abstract class FormataAlunos extends Formatter implements Comparator<Aluno>{

	private static final List<Aluno> cabecalhoAluno = new ArrayList<Aluno>();
	public static String caminho = "D:\\.Programacao\\Java\\PDS2\\alunos\\";

	private static String LeArquivo(String caminho) {
		
		String dadosArquivo = "";
		Scanner in;
		
		try {
			in = new Scanner(new FileReader(caminho + "alunos.csv"));
			while (in.hasNextLine()) {
			    String line = in.nextLine();
			    dadosArquivo += line + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return dadosArquivo;
	}

	private static List<Aluno> SeparaAlunosArquivo(String arquivoLido) {
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
			
			String[] dividePorLinha = arquivoLido.split("\n");
			
			int contador = 0;
			for(String linha : dividePorLinha){
				Aluno aluno = new Aluno();
				if(contador > 0) {
					
					String[] dividePorTipo = linha.split(",");

					aluno.nome = dividePorTipo[0];
					aluno.telefone = dividePorTipo[1];
					aluno.materia = dividePorTipo[2];
					aluno.nota =  dividePorTipo[3];
					
					listaAlunos.add(aluno);
				} else {
					String[] dividePorTipo = linha.split(",");

					aluno.nome = dividePorTipo[0];
					aluno.telefone = dividePorTipo[1];
					aluno.materia = dividePorTipo[2];
					aluno.nota =  dividePorTipo[3];
					
					cabecalhoAluno.add(aluno);
				}
				contador ++;
			}
		return listaAlunos;
	}

	private static List<Aluno> TruncaNotasAlunos(List<Aluno> listaAlunos) {
		
		for(Aluno aluno : listaAlunos) {
			String convertido = aluno.nota;
			int indice = StringUtils.indexOf(convertido, ".");
			if(indice > 0) {

				long restante = Long.parseLong(convertido.substring(indice+1));
				if (restante >= 5) {
					aluno.nota = ""+Math.ceil(restante);
				} else if (restante >= 0) {
					aluno.nota = ""+Math.floor(restante);
				}
			}
				
		}
		return listaAlunos;
	}
	
	private static QntCarecterColuna ObtemQntCarecterPorColuna(List<Aluno> listaAlunos) {
		QntCarecterColuna qntCaracteres = new QntCarecterColuna();
		
		for(Aluno aluno : listaAlunos) {
			qntCaracteres = ComparaQntAluno(aluno, qntCaracteres);
		}
		for(Aluno aluno : cabecalhoAluno) {
			qntCaracteres = ComparaQntAluno(aluno, qntCaracteres);
		}
		
		return qntCaracteres;
	}
	
	private static QntCarecterColuna ComparaQntAluno(Aluno aluno, QntCarecterColuna qntCaracteres) {
		if (aluno.nome.length()+1 > qntCaracteres.nome) {
			qntCaracteres.nome = aluno.nome.length()+1;
		}
		if (aluno.telefone.length()+1 > qntCaracteres.telefone) {
			qntCaracteres.telefone = aluno.telefone.length()+1;
		}
		if (aluno.materia.length()+1 > qntCaracteres.materia) {
			qntCaracteres.materia = aluno.materia.length()+1;
		}
		if (aluno.nota.toString().length()+1 > qntCaracteres.nota) {
			qntCaracteres.nota = aluno.nota.toString().length()+1;
		}
			
		return qntCaracteres;
	}

	private static void escreverArquivo(String path, String conteudoArquivo, String nomeArquivo){
		try{
			
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path+nomeArquivo));
			Scanner in = new Scanner(System.in);
			buffWrite.append(conteudoArquivo);
			buffWrite.close();
		} catch (Exception ex) {}
	}

	private static String OrdenaPorNome(List<Aluno> listaAlunosTruncado, QntCarecterColuna qntCaracteres) {
		String conteudoArquivo = "";

		for(Aluno al : cabecalhoAluno) {
			conteudoArquivo += alinharEsquerda(al.nome, qntCaracteres.nome) + alinharEsquerda(al.telefone, qntCaracteres.telefone) + alinharEsquerda(al.materia, qntCaracteres.materia) + alinharEsquerda(""+al.nota, qntCaracteres.nota) +"\n";
		}
		
		Collections.sort(listaAlunosTruncado, new Comparator<Aluno>(){
			public int compare(Aluno a, Aluno b)
		    {
		        return a.nome.compareTo(b.nome);
		    }
		});
		for(Aluno al : listaAlunosTruncado) {
			conteudoArquivo += alinharEsquerda(al.nome, qntCaracteres.nome) + alinharEsquerda(al.telefone, qntCaracteres.telefone) + alinharEsquerda(al.materia, qntCaracteres.materia) + alinharEsquerda(""+al.nota, qntCaracteres.nota) +"\n";
		}
		
		
		return conteudoArquivo;
		
	}
	

	private static String OrdenaPorNota(List<Aluno> listaAlunosTruncado, QntCarecterColuna qntCaracteres) {
		String conteudoArquivo = "";

		for(Aluno al : cabecalhoAluno) {
			conteudoArquivo += alinharEsquerda(al.nome, qntCaracteres.nome) + alinharEsquerda(al.telefone, qntCaracteres.telefone) + alinharEsquerda(al.materia, qntCaracteres.materia) + alinharEsquerda(""+al.nota, qntCaracteres.nota) +"\n";
		}
		
		Collections.sort(listaAlunosTruncado, new Comparator<Aluno>(){
			public int compare(Aluno a, Aluno b)
		    {
		        return b.nota.compareTo(a.nota);
		    }
		});
		for(Aluno al : listaAlunosTruncado) {
			conteudoArquivo += alinharEsquerda(al.nome, qntCaracteres.nome) + alinharEsquerda(al.telefone, qntCaracteres.telefone) + alinharEsquerda(al.materia, qntCaracteres.materia) + alinharEsquerda(""+al.nota, qntCaracteres.nota) +"\n";
		}
		
		
		return conteudoArquivo;
		
	}
	
	public static void main(String[] args){
		String arquivoLido = LeArquivo(caminho);
		List<Aluno> listaAlunos = SeparaAlunosArquivo(arquivoLido);
		List<Aluno> listaAlunosTruncado = TruncaNotasAlunos(listaAlunos);
		
		QntCarecterColuna qntCaracteres = ObtemQntCarecterPorColuna(listaAlunosTruncado);
		
		
		String conteudoArquivoNome = OrdenaPorNome(listaAlunosTruncado, qntCaracteres);
		escreverArquivo(caminho, conteudoArquivoNome, "alunos-nome.txt");
		
		String conteudoArquivoNota = OrdenaPorNota(listaAlunosTruncado, qntCaracteres);
		escreverArquivo(caminho, conteudoArquivoNota, "alunos-nota.txt");
	}





}
