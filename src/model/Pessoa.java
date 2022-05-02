package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import exceptions.CpfTamanhoException;

public abstract class Pessoa {
	
		protected String nome;
		protected String cpf;
		protected LocalDate dataNascimento;

		public Pessoa(String nome, String cpf, LocalDate dataNascimento) throws CpfTamanhoException {
			super();
			this.nome = nome;
			if (cpf.length() == 11) {
				this.cpf = cpf;
			} else {
				throw new CpfTamanhoException("Cpf deve conter 11 dígitos");
			}
			this.dataNascimento = dataNascimento;
		}

		@Override
		public String toString() {
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("YYYYMMdd");
			return nome + ";" + cpf + ";" + dataNascimento.format(dft);
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public LocalDate getDataNascimento() {
			return dataNascimento;
		}

		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cpf);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pessoa other = (Pessoa) obj;
			return Objects.equals(cpf, other.cpf);
		}
	}


