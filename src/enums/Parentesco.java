package enums;

public enum Parentesco {
	

	    FILHO(189.59), SOBRINHO(189.59), OUTROS(189.59);

	    private final Double desconto;

	    private Parentesco(Double desconto) {
	        this.desconto = desconto;
	    }

	    public Double getDesconto() {
	        return desconto;
	    }
	}


