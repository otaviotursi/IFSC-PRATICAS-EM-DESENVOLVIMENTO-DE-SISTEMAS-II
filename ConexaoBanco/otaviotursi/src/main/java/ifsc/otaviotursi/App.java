package ifsc.otaviotursi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.TextFormat.ParseException;


/*
 * 
 Comando para criar a tabela
 CREATE TABLE `desenv`.`aluno` (`ID` BIGINT NOT NULL AUTO_INCREMENT , `MATRICULA` TEXT NOT NULL , `NOME` TEXT NOT NULL , `TELEFONE` TEXT NOT NULL , `dataNascimento` DATE NOT NULL , `Curso` TEXT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;
 * 
 * */

public class App 
{
	private static Connection conn;
	private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static void ConectarBanco() throws SQLException, Exception {
		String username = "root";
		String password = "senha";
		
		String conexaoUrl = "jdbc:mysql://localhost:3306/DESENV";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(conexaoUrl, username, password);
		
		if(conn != null) {
			System.out.println("Conexão obtida com sucesso. "+conn);
		}
		
	}
	
	private static void InserirAluno(Aluno aluno) throws SQLException {
    	System.out.println( "Inserindo aluno" + aluno.ToString());
    	String query = "INSERT INTO `aluno`(`matricula`, `nome`, `telefone`, `dataNascimento`, `Curso`) VALUES ('"+aluno.matricula+"','"+aluno.nome+"','"+aluno.telefone+"','"+sdf.format(aluno.dataNascimento)+"','"+aluno.curso+"')";

    	System.out.println( "query: " + query);
    	conn.createStatement().execute(query);
    	
	}
	
	private static List<Aluno> SelecionarTodosAlunos() throws SQLException {

    	System.out.println( "Selecionando todos os alunos" );
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
    	ResultSet sqlAlunos = conn.createStatement().executeQuery("SELECT * FROM aluno");
		while(sqlAlunos.next()) {
			Aluno aluno = new Aluno();
	    	aluno.curso = sqlAlunos.getString("curso");
	    	aluno.matricula = sqlAlunos.getString("matricula");
	    	aluno.nome = sqlAlunos.getString("nome");
	    	aluno.dataNascimento = sqlAlunos.getDate("dataNascimento");
	    	aluno.telefone = sqlAlunos.getString("telefone");
	    	listaAlunos.add(aluno);
		}
		return listaAlunos;
	}
	 
	 
    public static void main( String[] args ) throws SQLException, Exception
    {
    	System.out.println( "Iniciando aplicação" );
    	List<Aluno> listaAlunos = new ArrayList<Aluno>();
    	Aluno aluno = new Aluno();
    	ConectarBanco();
    	
    	listaAlunos = SelecionarTodosAlunos();
    	System.out.println( "Listando alunos" );
    	for(Aluno lista : listaAlunos) {
        	System.out.println( aluno.ToString() );
    	}
    			
    	aluno = new Aluno();
    	aluno.nome = "Otavio";
    	aluno.dataNascimento = sdf.parse("2014-02-14");
    	aluno.matricula = "123456";
    	aluno.telefone = "11 98764";
    	aluno.curso = "ADS";
    	InserirAluno(aluno);
    	
    	aluno = new Aluno();
    	aluno.nome = "Robinson";
    	aluno.dataNascimento = sdf.parse("2002-06-11");
    	aluno.matricula = "484652";
    	aluno.telefone = "48 984230";
    	aluno.curso = "Eng. Civil";
    	InserirAluno(aluno);
    	
    	aluno = new Aluno();
    	aluno.nome = "Carol";
    	aluno.dataNascimento = sdf.parse("1999-12-04");
    	aluno.matricula = "543254";
    	aluno.telefone = "47 3443";
    	aluno.curso = "Moda";
    	InserirAluno(aluno);
    	
    	
    	listaAlunos = SelecionarTodosAlunos();
    	System.out.println( "Listando alunos" );
    	for(Aluno lista : listaAlunos) {
        	System.out.println( lista.ToString() );
    	}
    	
    }
}
