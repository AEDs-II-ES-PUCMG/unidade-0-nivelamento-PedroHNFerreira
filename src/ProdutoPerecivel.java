import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto{

    private double DESCONTO = 0.25;
    private int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataDeValidade){
        super(desc, precoCusto, margemLucro);
        if(dataDeValidade.isAfter(LocalDate.now())){
            this.dataDeValidade = dataDeValidade;
        } else {
            throw new IllegalArgumentException("O produto está vencido!");
        }

    }

    @Override
    public double valorDeVenda(){
        LocalDate hoje = LocalDate.now();
        LocalDate tempo_desconto = dataDeValidade.minusDays(PRAZO_DESCONTO);
        if(hoje.isBefore(dataDeValidade) && hoje.isAfter(tempo_desconto)){
            return ((precoCusto * (1.0 + margemLucro))*(1-DESCONTO));
        }else{
            return (precoCusto * (1.0 + margemLucro));
        }
    }

    @Override
	public String toString() {
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String dados = super.toString();
        dados += "\nVálido até " + formato.format(dataDeValidade);
        return dados;
	}

    @Override
    public String gerarDadosTexto() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataFormatada = String.format(String.valueOf(dataDeValidade), formatoData);
        String precoFormatado = String.format("%.2f", precoCusto).replace(',', '.');
        String margemFormatada = String.format("%.2f", margemLucro).replace(',', '.');

        String linha = String.format("2;%s;%s;%s;%s", getDescricao(), precoFormatado, margemFormatada, dataFormatada);
        return linha;
    }
}