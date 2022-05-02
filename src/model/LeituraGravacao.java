package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Set;

public class LeituraGravacao {

	
		 public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		        EntradaSaidaArquivo esa = new EntradaSaidaArquivo();
		        Scanner leitor = new Scanner(System.in);
		        System.out.println("Digite o nome do arquivo de entrada");
		        String nomeArquivo;
		        nomeArquivo = leitor.next();
		        System.out.println();
		        System.out.println("Conteudo do arquivo de entrada --");
		        System.out.println();
		        Set<Funcionario> lista = esa.leituraDoArquivo(nomeArquivo);
		        System.out.println("--------------------------------------");
		        System.out.println("Digite o nome do arquivo de saida");
		        nomeArquivo = leitor.next();
		        esa.escrevendoArquivo(lista,nomeArquivo);
		        System.out.println("---------------------------");
		        System.out.println("Arquivo criado com sucesso");
		        leitor.close();

	}

}
