import java.text.NumberFormat;

public abstract class Produto { 
    
    protected static final double MARGEM_PADRAO = 0.2; 
    protected String descricao;
    protected double precoCusto; 
    protected double margemLucro; 
    
 
    private void init(String desc, double precoCusto, double margemLucro) {
        if ((desc != null && desc.length() >= 3) && (precoCusto > 0.0) && (margemLucro > 0.0)) {
            this.descricao = desc;
            this.precoCusto = precoCusto;
            this.margemLucro = margemLucro;
        } else {
            throw new IllegalArgumentException("Valores inválidos para os dados do produto.");
        }
    }
    
   
    protected Produto(String desc, double precoCusto, double margemLucro) { 
        init(desc, precoCusto, margemLucro);
    }
    
   
    protected Produto(String desc, double precoCusto) { 
        init(desc, precoCusto, MARGEM_PADRAO);
    }
    
    
    public double valorVenda() { 
        return (precoCusto * (1.0 + margemLucro));
    }
    
    @Override
    public String toString() { 
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s: %s", descricao, moeda.format(valorVenda()));
    }
}