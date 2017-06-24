
package Model;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable{
    private String codImovel;
    private float valorDaVenda; // valor que foi vendido o imovel.
    private String nomeComprador; // nome do comprador.
    private Date dataVenda; // data que foi vendido o imovel.
    private String corretorResponsavel; // nome do corretor que vendeu o imovel.

    public Venda(String codImovel, float valorDaVenda, String nomeComprador, Date dataVenda, String corretorResponsavel) {
        this.codImovel = codImovel;
        this.valorDaVenda = valorDaVenda;
        this.nomeComprador = nomeComprador;
        this.dataVenda = dataVenda;
        this.corretorResponsavel = corretorResponsavel;
    }

    public String getCodImovel() {
        return codImovel;
    }

    public float getValorDaVenda() {
        return valorDaVenda;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public String getCorretorResponsavel() {
        return corretorResponsavel;
    }
       
}
