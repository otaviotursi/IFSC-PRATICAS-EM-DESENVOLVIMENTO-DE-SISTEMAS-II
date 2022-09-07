package ifsc.otaviotursi;

import java.sql.Date;

public class Aluno {
	String nome;
	String matricula;
	String curso;
	String telefone;
	java.util.Date dataNascimento;
	
	public String ToString() {
		return "Nome: " + nome + " | Matricula: " + matricula + " | Curso: " + curso + " | Telefone: " + telefone + " | Data de Nascimento: " + dataNascimento;
	}
}
