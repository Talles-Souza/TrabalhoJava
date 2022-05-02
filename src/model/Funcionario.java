package model;


import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import exceptions.CpfTamanhoException;
import exceptions.IdadeException;
import interfaces.Calculos;

	public class Funcionario extends Pessoa implements Calculos {
		private Double salarioBruto;
		private Set<Dependente> dependentes = new HashSet<Dependente>();

		public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto)
				throws CpfTamanhoException, IdadeException {
			super(nome, cpf, dataNascimento);
			Period periodo = dataNascimento.until(LocalDate.now());
			if (periodo.getYears() < 18) {
				throw new IdadeException("Funcionario tem que ter mais de 18 anos");
			}
			this.salarioBruto = salarioBruto;
		}

		@Override
		public String toString() {
			return super.toString() + ";" + salarioBruto + dependentes;
		}

		public Double getSalarioBruto() {
			return salarioBruto;
		}

		public Set<Dependente> getDependente() {
			return dependentes;
		}

		@Override
		public Double descontoInss() {
			double calculoInss = 0.0;
			if (this.salarioBruto < 1212.00) {
				calculoInss = this.salarioBruto * 0.075;
				return calculoInss;
			} else if (this.salarioBruto < 2427.35) {
				calculoInss = (this.salarioBruto * 0.09) - 18.18;
				return calculoInss;
			} else if (this.salarioBruto < 3641.03) {
				calculoInss = this.salarioBruto * 0.12 - 91.00;
				return calculoInss;
			} else if (this.salarioBruto < 7087.22) {
				calculoInss = this.salarioBruto * 0.14 - 163.82;
				return calculoInss;
			} else if (this.salarioBruto > 7087.22) {
				calculoInss = 7087.22 * 0.14 - 163.82;
				return calculoInss;
			}
			return calculoInss;
		}

		private final Double calculoBase() {
			double valorSalarioDependenteInss = 0.0;
			for (Dependente dependente : dependentes) {
				valorSalarioDependenteInss += dependente.getTipoParentesco().getDesconto();
			}
			return this.salarioBruto - valorSalarioDependenteInss - this.descontoInss();
		}

		public Double descontoIR() {
			double calculoIR = 0.0;
			if (calculoBase() < 1903.98) {
				return calculoIR = 0;
			} else if (calculoBase() < 2826.65) {
				calculoIR = ((calculoBase()) * 0.075) - 142.80;
				return calculoIR;
			} else if (calculoBase() < 3751.05) {
				calculoIR = ((calculoBase()) * 0.15) - 354.80;
				return calculoIR;
			} else if (calculoBase() < 4664.68) {
				calculoIR = ((calculoBase()) * 0.225) - 636.13;
				return calculoIR;
			} else if (calculoBase() > 4664.68) {
				calculoIR = ((calculoBase()) * 0.275) - 869.36;
			}
			return calculoIR;
		}

		public double salarioliquido() {
			double valor = 0.0;
			valor = this.salarioBruto - descontoIR() - descontoInss();
			return valor;
		}
	}


