
package Model;

import java.io.Serializable;

public class Venda implements Serializable{
    private float valorDaVenda; // valor que foi vendido o imovel.
    private String nomeComprador; // nome do comprador.
    private int dataDaVenda; // data que foi vendido o imovel.
    private String nomeCorretorVenda; // nome do corretor que vendeu o imovel.

    public Venda(float valorDaVenda, String nomeComprador, int dataDaVenda, String nomeCorretorVenda) {
        this.valorDaVenda = valorDaVenda;
        this.nomeComprador = nomeComprador;
        this.dataDaVenda = dataDaVenda;
        this.nomeCorretorVenda = nomeCorretorVenda;
    }

    public float getValorDaVenda() {
        return valorDaVenda;
    }

    public void setValorDaVenda(float valorDaVenda) {
        this.valorDaVenda = valorDaVenda;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public int getDataDaVenda() {
        return dataDaVenda;
    }

    public void setDataDaVenda(int dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }

    public String getNomeCorretorVenda() {
        return nomeCorretorVenda;
    }

    public void setNomeCorretorVenda(String nomeCorretorVenda) {
        this.nomeCorretorVenda = nomeCorretorVenda;
    }
    
}
