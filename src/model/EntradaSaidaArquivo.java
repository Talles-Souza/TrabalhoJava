package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import enums.Parentesco;
import exceptions.CpfTamanhoException;
import exceptions.IdadeException;

public class EntradaSaidaArquivo {

	public Set<Funcionario> leituraDoArquivo(String arquivo) throws UnsupportedEncodingException, FileNotFoundException {
		Set<Funcionario> listaFuncionarios = new HashSet<>();
		Funcionario funcionario = null;
		Scanner leitura = new Scanner(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
		try {
			while (leitura.hasNextLine()) {
				String linha = leitura.nextLine();
				if (!linha.isEmpty()) {
					String[] dados = linha.split(";");
					if (funcionario != null) {
						String nomeDependente = dados[0], cpfDependente = dados[1], nascimentoDependente = dados[2],
								tipoParentesco = dados[3];
						Dependente dependente = new Dependente(nomeDependente, cpfDependente,
								LocalDate.parse(nascimentoDependente, DateTimeFormatter.BASIC_ISO_DATE),
								Parentesco.valueOf(tipoParentesco));
						funcionario.getDependente().add(dependente);
						continue;
					}
					String nomeFuncionario = dados[0], cpfFuncionario = dados[1], nascimentoFuncionario = dados[2],
							salarioBruto = dados[3];
					funcionario = new Funcionario(nomeFuncionario, cpfFuncionario,
							LocalDate.parse(nascimentoFuncionario, DateTimeFormatter.BASIC_ISO_DATE),
							Double.parseDouble(salarioBruto));
				} else {
					listaFuncionarios.add(funcionario);
					funcionario = null;
				}
			}
			if (funcionario != null) {
				listaFuncionarios.add(funcionario);
				leitura.close();
			}
		} catch (CpfTamanhoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IdadeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		for (Funcionario funcionarios : listaFuncionarios) {
			System.out.println(funcionarios);
			System.out.println();
		}
		return listaFuncionarios;
	}

	public void escrevendoArquivo(Set<Funcionario> listaFuncionarios, String arquivo) {
		DecimalFormat deci = new DecimalFormat("0.00");
		FileWriter arquivoSaida = null;
		try {
			arquivoSaida = new FileWriter(arquivo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		PrintWriter print = new PrintWriter(arquivoSaida);

		for (Funcionario funcionario : listaFuncionarios) {
			String linha = funcionario.getNome() + ";" + funcionario.getCpf() + ";"+ deci.format(funcionario.descontoInss()) + ";" + deci.format(funcionario.descontoIR()) + ";"+ deci.format(funcionario.salarioliquido()) + "\n";
			print.print(linha);
		}
		print.close();
	}
}
