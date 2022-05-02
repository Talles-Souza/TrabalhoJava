package model;


import java.time.LocalDate;
import java.time.Period;

import enums.Parentesco;
import exceptions.CpfTamanhoException;
import exceptions.IdadeException;

	public class Dependente extends Pessoa {
	    private Parentesco tipoParentesco;

	    public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco tipoParentesco)
	            throws IdadeException, CpfTamanhoException {
	        super(nome, cpf, dataNascimento);
	        Period periodo = dataNascimento.until(LocalDate.now());
	        if (periodo.getYears() > 18) {
	            throw new IdadeException("Dependente deve ter menos de 18 anos");
	        }
	        this.tipoParentesco = tipoParentesco;
	    }

	    @Override
	    public String toString() {
	        return "\n" + super.toString() + ";" + tipoParentesco;
	    }

	    public Parentesco getTipoParentesco() {
	        return tipoParentesco;
	    }
	}


